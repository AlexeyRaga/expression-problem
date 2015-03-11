package pragmatic

trait Expr
case class Lit(value: Int) extends Expr
case class Add[A <: Expr, B <: Expr](x: A, y: B) extends Expr