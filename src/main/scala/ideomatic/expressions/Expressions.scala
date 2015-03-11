package ideomatic.expressions

import ideomatic._
import evaluate._

case class Mul[A: Expr , B: Expr](x: A, y: B)

object Mul {

  implicit def mulExpr[A: Expr, B: Expr] : Expr[Mul[A, B]] = new Expr[Mul[A, B]] {}

  implicit def mulEval[A: Expr: Eval, B: Expr: Eval] : Eval[Mul[A, B]] = {
    val evA = implicitly[Eval[A]]
    val evB = implicitly[Eval[B]]
    new Eval[Mul[A, B]] {
      def eval(expr: Mul[A, B])(implicit ex: Expr[Mul[A, B]]) = evA.eval(expr.x) * evB.eval(expr.y)
    }
  }
}
