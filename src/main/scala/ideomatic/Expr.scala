package ideomatic

trait Expr[A]
case class Lit(value: Int)
case class Add[A: Expr, B: Expr](x: A, y: B)

object Lit {
  implicit object LitExpr extends Expr[Lit]
}

object Add {
  implicit def addExpr[A: Expr, B: Expr] : Expr[Add[A, B]] = new Expr[Add[A, B]] {}
}