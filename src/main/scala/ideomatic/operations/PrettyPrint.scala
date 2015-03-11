package ideomatic.operations

import ideomatic._
import expressions._

trait PrettyPrint[A] {
  def print(expr: A)(implicit ex: Expr[A]) : String
}

object PrettyPrint {
  def prettyPrint[A: Expr: PrettyPrint](expr: A) = {
    implicitly[PrettyPrint[A]].print(expr)
  }

  implicit object PrintLit extends PrettyPrint[Lit] { def print(expr: Lit)(implicit ex: Expr[Lit]) = expr.value.toString }

  implicit def mulPrint[A: Expr: PrettyPrint, B: Expr: PrettyPrint]: PrettyPrint[Mul[A, B]] = {
    val pA = implicitly[PrettyPrint[A]]
    val pB = implicitly[PrettyPrint[B]]
    new PrettyPrint[Mul[A, B]] {
      def print(expr: Mul[A, B])(implicit ex: Expr[Mul[A, B]]) = pA.print(expr.x) + " * " + pB.print(expr.y)
    }
  }

  implicit def addPrint[A: Expr: PrettyPrint, B: Expr: PrettyPrint]: PrettyPrint[Add[A, B]] = {
    val pA = implicitly[PrettyPrint[A]]
    val pB = implicitly[PrettyPrint[B]]
    new PrettyPrint[Add[A, B]] {
      def print(expr: Add[A, B])(implicit ex: Expr[Add[A, B]]) = s"(${pA.print(expr.x)} + ${pB.print(expr.y)})"
    }
  }
}