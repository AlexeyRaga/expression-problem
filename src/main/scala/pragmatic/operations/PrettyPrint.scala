package pragmatic.operations

import pragmatic._
import pragmatic.expressions._

trait PrettyPrint[A <: Expr] {
  def print(expr: A) : String
}

object PrettyPrint {
  def prettyPrint[A <: Expr](expr: A)(implicit pa: PrettyPrint[A]) = pa.print(expr)

  implicit object PrintLit extends PrettyPrint[Lit] { def print(expr: Lit) = expr.value.toString }

  implicit def mulPrint[A <: Expr, B <: Expr](implicit pA: PrettyPrint[A], pB: PrettyPrint[B]) : PrettyPrint[Mul[A, B]] = {
    new PrettyPrint[Mul[A, B]] {
      def print(expr: Mul[A, B]) = pA.print(expr.x) + " * " + pB.print(expr.y)
    }
  }

  implicit def addPrint[A <: Expr, B <: Expr](implicit pA: PrettyPrint[A], pB: PrettyPrint[B]) : PrettyPrint[Add[A, B]] = {
    new PrettyPrint[Add[A, B]] {
      def print(expr: Add[A, B]) = s"(${pA.print(expr.x)} + ${pB.print(expr.y)})"
    }
  }
}