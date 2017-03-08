/**
  * Created by yarenty on 08/03/2017.
  */

import scalaz._
import Scalaz._

object Learn7_Functors {

  def main(args: Array[String]): Unit = {

    println((1, 2, 3) map {
      _ + 1
    })

    val a = ((x: Int) => x + 1) map {
      _ * 7
    }

    println(a(3))

    // in new version of scalaz there is no more fmap - instead map is used ....
    // TODO: how to play with it ?
    //
    //    final def map[B](f: A => B): F[B] = F.map(self)(f)
    //
    //    fmap :: (a -> b) -> (r -> a) -> (r -> b)
    //
    //    val u = fmap (*3) (+100) 1


    //    :t map (*3) (+100) 1


    println(List(1, 2, 3) map {
      3 *
    })


    val u = Functor[List].lift {
      (_: Int) * 2
    }

    println(u(List(3)))

    println(List(1, 2, 3) >| "x")
    println(List(1, 2, 3) as "x")
    println(List(1, 2, 3).fpair)
    println(List(1, 2, 3).strengthL("x"))
    println(List(1, 2, 3).strengthR("x"))
    println(List(1, 2, 3).void)

  }

}
