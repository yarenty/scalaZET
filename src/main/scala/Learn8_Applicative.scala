/**
  * Created by yarenty on 08/03/2017.
  */

import scalaz.Scalaz._
import scalaz._

object Learn8_Applicative {

  def main(args: Array[String]): Unit = {

    // how to map functions with more than one params like *

    val mult = List(1, 2, 3, 4) map {
      (_: Int) * (_: Int)
    }.curried

    println(mult map (_ (9)))

 println
    println(1.point[List])
    println(1.point[Option])
    println(1.point[Option] map {
      _ + 2
    })
    println(1.point[List] map {
      _ + 2
    })


    println
    //*> and <* are variations that returns only the rhs or lhs.
    println( 1.some <* 2.some)
    println( none <* 2.some )
    println( 1.some *> 2.some )
    println( none *> 2.some )

    println
    //<*>
    println( 9.some <*> {(_: Int) + 3}.some )
    println( 3.some <*> { 9.some <*> {(_: Int) + (_: Int)}.curried.some } )


    println
    //extracts values from containers and apply them to a single function
    println( ^(3.some, 5.some) {_ + _})
    println( ^(3.some, none[Int]) {_ + _} )

    println
    println( (3.some |@| 5.some) {_ + _} )

    println

    println(List(1, 2, 3) <*> List((_: Int) * 0, (_: Int) + 100, (x: Int) => x * x))
    println(List(3, 4) <*> { List(1, 2) <*> List({(_: Int) + (_: Int)}.curried, {(_: Int) * (_: Int)}.curried) })
    println((List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _})

    println
    //zip list
    val z = streamZipApplicative.ap(Tags.Zip(Stream(1, 2))) (Tags.Zip(Stream({(_: Int) + 3}, {(_: Int) * 2})))
    println(z)

    println
    val app = Apply[Option].lift2((_: Int) :: (_: List[Int]))

    println(app(3.some, List(4).some))



    println

    def sequenceA[F[_]: Applicative, A](list: List[F[A]]): F[List[A]] = list match {
      case Nil     => (Nil: List[A]).point[F]
      case x :: xs => (x |@| sequenceA(xs)) {_ :: _}
    }

    println(sequenceA(List(1.some, 2.some)))
    println(sequenceA(List(3.some, none, 1.some)))
    println(sequenceA(List(List(1, 2, 3), List(4, 5, 6))))


    println
    type Function1Int[A] = ({type l[A]=Function1[Int, A]})#l[A]

    val sa = sequenceA(List((_: Int) + 3, (_: Int) + 2, (_: Int) + 1): List[Function1Int[Int]])

    println(sa(2))
    println()

  }

}
