package com.yarenty.streams

/**
  * Created by yarenty on 21/03/17.
  */
object PouringTest {

  def main(args: Array[String]): Unit = {
    val problem = new Pouring(Vector(4,7))

    println(problem.moves)


    val problem2 = new Pouring(Vector(4,7,9))

    println(problem2.moves)
  }



}
