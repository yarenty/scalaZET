package com.yarenty.streams

import scala.collection.mutable

/**
  * Created by yarenty on 21/03/17.
  */
class Pouring(capacity:Vector[Int]) {

  //States
  type State = Vector[Int]

  val initialState:Vector[Int] = capacity map ( x => 0)

  //Moves
  trait Move {
    def change(state:State):State
  }
   case class Empty(glass:Int) extends Move {
     override def change(state: State) = state updated (glass,0)
   }
   case class Pour(from:Int, to:Int) extends Move {
     override def change(state: State) = {
       val amount = state(from) min (capacity(to) - state(to))
       state updated (from, state(from) - amount)  updated (to, state(to) + amount)

     }
   }
   case class Fill(glass:Int) extends Move {
     override def change(state: State) = state updated ( glass, capacity(glass))
   }

  val glasses:Range = capacity.indices

  val moves =
    ( for (g <- glasses) yield Empty(g)) ++
    ( for (g <- glasses) yield Fill(g)) ++
    ( for (from <- glasses; to <- glasses if from!=to ) yield Pour(from,to))


  //paths
  class Path(history: List[Move]) { // last move in path is first in history

    def endState: State = trackState(history)

    private def trackState(xs: List[Move]): State = xs match {
      case Nil => initialState
      case move :: xs1 => move change trackState(xs1)
    }


  }

}
