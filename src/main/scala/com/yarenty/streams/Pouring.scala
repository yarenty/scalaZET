package com.yarenty.streams

/**
  * Created by yarenty on 21/03/17.
  */
class Pouring(capacity: Vector[Int]) {

  //States
  type State = Vector[Int]

  val initialState: Vector[Int] = capacity map (x => 0)

  //Moves
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    override def change(state: State) = state updated(glass, 0)
  }

  case class Pour(from: Int, to: Int) extends Move {
    override def change(state: State) = {
      val amount = state(from) min (capacity(to) - state(to))
      state updated(from, state(from) - amount) updated(to, state(to) + amount)

    }
  }

  case class Fill(glass: Int) extends Move {
    override def change(state: State) = state updated(glass, capacity(glass))
  }

  val glasses: Range = capacity.indices

  val moves =
    (for (g <- glasses) yield Empty(g)) ++
      (for (g <- glasses) yield Fill(g)) ++
      (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))


  //paths
  class Path(history: List[Move], val endState: State) {
    // last move in path is first in history
    //    def endState: State = (history foldRight initialState)(_ change _) // speed up
    def extended(move: Move) = new Path(move :: history, move change endState)

    override def toString: String = (history.reverse mkString " ") + " --> " + endState

  }

  val initialPath = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extended
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }

  val pathSets = from(Set(initialPath), Set(initialState))


  def solution(target: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
}
