/**
  * Created by yarenty on 09/03/2017.
  */
import scalaz._
import Scalaz._

object Learn9_Kinds {


  trait Test {
    type F[_]
  }

  def main(args: Array[String]): Unit = {
    println( List(1, 2, 3).shows )


   val f =  Functor[List].lift((_: Int) + 2)

    println ( f(List(3)) )
  }
}
