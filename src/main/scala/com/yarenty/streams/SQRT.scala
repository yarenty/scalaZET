package com.yarenty.streams

/**
  *
  * Created by yarenty on 21/03/17.
  */
object SQRT {


  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2

    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }

  def isGoodEnough(guess: Double, x: Double): Boolean =
    math.abs((guess * guess - x) / x) < 0.0001

  def main(args: Array[String]): Unit = {

    val sqrt = sqrtStream(3)

    println("SQRT:")
    println(sqrt.take(20).toList)
    println(sqrtStream(3).filter(isGoodEnough(_, 3)).take(10).toList)

  }

}
