/**
  * Created by yarenty on 07/03/2017.
  */

import scalaz.Scalaz._
import scalaz._

/**
  * typeclases 102
  */
object Learn5_typeclasses_102 {
//
//  sealed trait TrafficLight
//  case object Red extends TrafficLight
//  case object Yellow extends TrafficLight
//  case object Green extends TrafficLight


  implicit val TrafficLightEqual: Equal[TrafficLight] = Equal.equal(_ == _)

  case class TrafficLight(name: String)
  val red = TrafficLight("red")
  val yellow = TrafficLight("yellow")
  val green = TrafficLight("green")




  def main(args: Array[String]): Unit = {

    //Red === Yellow
    println( red === yellow )


  }
}
