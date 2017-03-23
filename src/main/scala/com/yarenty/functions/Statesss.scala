package com.yarenty.functions

/**
  * Created by yarenty on 23/03/17.
  */
object Statesss {


  def iterate(n: Int, f: Int => Int, x: Int): Int =
    if (n == 0) x else iterate(n - 1, f, f(x))

  def square(c: Int) = c * c


  def main(args: Array[String]): Unit = {

    val o = iterate(1, square, 3)
    println(o)
  }
}
