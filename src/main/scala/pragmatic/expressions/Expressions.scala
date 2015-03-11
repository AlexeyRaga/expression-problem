package pragmatic.expressions

import pragmatic._
import evaluate._

case class Mul[A <: Expr, B <: Expr](x: A, y: B) extends Expr

object Mul {

  implicit def mulEval[A <: Expr, B <: Expr](implicit evA: Eval[A], evB: Eval[B]) : Eval[Mul[A, B]] = {
    new Eval[Mul[A, B]] {
      def eval(expr: Mul[A, B]) = evA.eval(expr.x) * evB.eval(expr.y)
    }
  }
}