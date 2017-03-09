/**
  * Created by yarenty on 09/03/2017.
  */

import scalaz._
import Scalaz._

object Learn10_Tagged {


  sealed trait KiloGram

  def KiloGram[A](a: A): A @@ KiloGram = Tag[A, KiloGram](a)

  //2
  sealed trait JoulePerKiloGram

  def JoulePerKiloGram[A](a: A): A @@ JoulePerKiloGram = Tag[A, JoulePerKiloGram](a)

  def energyR(m: Double @@ KiloGram): Double @@ JoulePerKiloGram =
    JoulePerKiloGram(299792458.0 * 299792458.0 * Tag.unsubst[Double, Id, KiloGram](m))



  def main(args: Array[String]): Unit = {

    val mass = KiloGram(20.0)

    // println(2 * mass )  /// not really working
    println(2 * Tag.unwrap(mass))

    println(3 * scalaz.Tag.unsubst[Double, Id, KiloGram](mass))

    //2

    println( energyR(mass) )

    // energyR(10.0) - this will not work
    //passing in plain Double to energyR fails at compile-time.
    // This sounds exactly like newtype except it’s even better because we can define Int @@ KiloGram if we want.
  }
}
