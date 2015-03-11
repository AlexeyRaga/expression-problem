package ideomatic.evaluate

import ideomatic._

trait Eval[A] {
  def eval(expr: A)(implicit ex: Expr[A]) : Int
}

object Eval {
  def evaluate[A: Expr: Eval](expr: A) = {
    implicitly[Eval[A]].eval(expr)
  }

  implicit object EvLit extends Eval[Lit] { def eval(expr: Lit)(implicit ev: Expr[Lit]) = expr.value }

  implicit def addEval[A: Expr: Eval, B: Expr: Eval] : Eval[Add[A, B]] = {
    val evA = implicitly[Eval[A]]
    val evB = implicitly[Eval[B]]
    new Eval[Add[A, B]] {
      def eval(expr: Add[A, B])(implicit ex: Expr[Add[A, B]]) = evA.eval(expr.x) + evB.eval(expr.y)
    }
  }
}

