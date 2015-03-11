package pragmatic.evaluate

import pragmatic._

trait Eval[A <: Expr] {
  def eval(expr: A) : Int
}

object Eval {
  def evaluate[A <: Expr](expr: A)(implicit evA: Eval[A]) = evA.eval(expr)

  implicit object EvLit extends Eval[Lit] { def eval(expr: Lit) = expr.value }

  implicit def addEval[A <: Expr, B <: Expr](implicit evA: Eval[A], evB: Eval[B]) : Eval[Add[A, B]] = {
    new Eval[Add[A, B]] {
      def eval(expr: Add[A, B]) = evA.eval(expr.x) + evB.eval(expr.y)
    }
  }
}

