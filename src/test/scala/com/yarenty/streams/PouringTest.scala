package com.yarenty.streams

/**
  * Created by yarenty on 21/03/17.
  */
object PouringTest {

  def main(args: Array[String]): Unit = {
    val problem = new Pouring(Vector(4,7))

    println(problem.moves)

    println(problem.pathSets.take(3).toList)

    println("SOLUTION 4,7 => 6")
    println(problem.solution(6))


    val problem2 = new Pouring(Vector(4,9))
    println(problem2.moves)
    println("SOLUTION 4,9 => 6")
    println(problem2.solution(6))




//

  }



}
