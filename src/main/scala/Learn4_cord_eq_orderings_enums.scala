/**
  * Created by yarenty on 07/03/2017.
  */

import scalaz._
import Scalaz._

object Learn4_cord_eq_orderings_enums {

  def main(args: Array[String]): Unit = {

    "hello".println


    println(3.show) //cord - purely functional structure for long strings

    println(3.shows) // just string

    println
    println( 1 =/= 2)  //equals
    println( 1 === 2)


    println
    println( 1 ?|? 2) //ordering
    println( 2 ?|? 1) //ordering

    println
    println( 'a' to 'e')
    println( 'a' |-> 'e')
    println( 3 |-> 5)
    println( 3 |--> (2, 14) )

    println
    println('B'.succ)
    println('B' -+- 3)
    println('E' --- 3)

  }
}
