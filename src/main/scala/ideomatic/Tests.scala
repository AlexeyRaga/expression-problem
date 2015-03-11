package ideomatic

object BasicTest extends App {
  import evaluate._

  val expr = Add(Lit(15), Lit(6))
  println(Eval.evaluate(expr))
}

object ExpressionsTest extends App {
  import evaluate._
  import expressions._

  val expr = Mul(Lit(2), Add(Lit(20), Lit(2)))
  println(Eval.evaluate(expr))
}


object OperationsTest extends App {
  import expressions._
  import operations._

  val expr = Mul(Lit(2), Add(Lit(15), Lit(6)))
  println(PrettyPrint.prettyPrint(expr))
}

object FullTest extends App {
  import expressions._
  import operations._
  import evaluate._

  val expr = Mul(Lit(2), Add(Lit(15), Lit(6)))
  println(PrettyPrint.prettyPrint(expr) + " = " + Eval.evaluate(expr))
}