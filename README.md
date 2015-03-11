# expression-problem
Solving the expression problem with typeclasses in Scala

The project contains two solutions: a "pragmatic" and an "ideomatic" ones.
The difference between them is the way the expression data type is encoded.

The "**pragmatic**" one uses an *Expr* trait which is then extended by every expression.
The "**ideomatic**" one declares the *Expr* as a typeclass, similarly how it would be done in Haskell since there is no other way in Haskell to have the open-for-extentsion ADT.
