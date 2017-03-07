/**
  * Created by yarenty on 07/03/2017.
  */

import scalaz.Scalaz._
import scalaz._

/**
  *  Yes-No typeclass
  */
object Learn6 {



  trait CanTruthy[A] { self =>
    /** @return true, if `a` is truthy. */
    def truthys(a: A): Boolean
  }
  object CanTruthy {
    def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev
    def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
      def truthys(a: A): Boolean = f(a)
    }
  }
  trait CanTruthyOps[A] {
    def self: A
    implicit def F: CanTruthy[A]
    final def truthy: Boolean = F.truthys(self)
  }
  object ToCanIsTruthyOps {
    implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) =
      new CanTruthyOps[A] {
        def self = v
        implicit def F: CanTruthy[A] = ev
      }
  }

 // IMPLICITS

  implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
    case 0 => false
    case _ => true
  })

  implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys({
    case Nil => false
    case _   => true
  })

  implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)

  implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] = CanTruthy.truthys(_ => false)


  def main(args: Array[String]): Unit = {

    import ToCanIsTruthyOps._
    //Red === Yellow
    println( 10.truthy )
    println( List("foo").truthy )

    // println(Nil.truthy) // ERROR! if nilCanTruthy is not defined
     println(false.truthy)



    //IF
    def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) =
      if (cond.truthy) ifyes
      else ifno


   println( truthyIf (Nil) {"YEAH!"} {"NO!"} )  // and here Nil is working!!
   println( truthyIf (2 :: 3 :: 4 :: Nil) {"YEAH!"} {"NO!"} )
   println( truthyIf (true) {"YEAH!"} {"NO!"} )


  }
}
