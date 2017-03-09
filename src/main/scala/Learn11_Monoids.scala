/**
  * Created by yarenty on 09/03/2017.
  */

import scalaz.Scalaz._
import scalaz._

object Learn11_Monoids {


  def main(args: Array[String]): Unit = {

    println(List(1, 2, 3) mappend List(4, 5, 6))

    println("one" mappend "two")

    println
    println(List(1, 2, 3) |+| List(4, 5, 6))

    println("one" ⊹ "two")


    println()
    println(Monoid[List[Int]].zero)
    println(Monoid[String].zero)


    println("\nTAGS .Multiplication")
    println(Tags.Multiplication(10) |+| Monoid[Int @@ Tags.Multiplication].zero)
    println(10 |+| Monoid[Int].zero)

    println("\nTags.Disjunction and Tags.Conjunction")
    println(Tags.Disjunction(true) |+| Tags.Disjunction(false))
    println(Monoid[Boolean @@ Tags.Disjunction].zero |+| Tags.Disjunction(true))
    println(Monoid[Boolean @@ Tags.Disjunction].zero |+| Monoid[Boolean @@ Tags.Disjunction].zero)
    println(Monoid[Boolean @@ Tags.Conjunction].zero |+| Tags.Conjunction(true))
    println(Monoid[Boolean @@ Tags.Conjunction].zero |+| Tags.Conjunction(false))

    println("\nOrdering")

    println((Ordering.LT: Ordering) |+| (Ordering.GT: Ordering))
    println((Ordering.GT: Ordering) |+| (Ordering.LT: Ordering))
    println(Monoid[Ordering].zero |+| (Ordering.LT: Ordering))
    println(Monoid[Ordering].zero |+| (Ordering.GT: Ordering))


    println

    def lengthCompare(lhs: String, rhs: String): Ordering =
      (lhs.length ?|? rhs.length) |+| (lhs ?|? rhs)


    println(lengthCompare("zen", "ants")) //“zen” is LT compared to “ants” because it’s shorter.
    println(lengthCompare("zen", "ant"))


  }
}
