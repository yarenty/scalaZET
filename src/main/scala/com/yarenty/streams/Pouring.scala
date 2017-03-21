package com.yarenty.streams

/**
  * Created by yarenty on 21/03/17.
  */
class Pouring(capacity:Vector[Int]) {

  //States
  type State = Vector[Int]

  val initialState:Vector[Int] = capacity map ( x => 0)

  //Moves
  trait Move
   case class Empty(glass:Int) extends Move
   case class Pour(from:Int, to:Int) extends Move
   case class Fill(glass:Int) extends Move

  val glasses:Range = capacity.indices

  val moves =
    ( for (g <- glasses) yield Empty(g)) ++
    ( for (g <- glasses) yield Fill(g)) ++
    ( for (from <- glasses; to <- glasses if from!=to ) yield Pour(from,to))


}
