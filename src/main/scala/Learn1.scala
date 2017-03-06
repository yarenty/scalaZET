/**
  * Created by yarenty on 06/03/2017.
  */
trait Monoid[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}

object IntMonoid extends Monoid[Int] {
  def mappend(a: Int, b: Int): Int = a + b
  def mzero: Int = 0
}



object Learn1 {





  def sum(xs: List[Int]): Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)

  def main(args: Array[String]): Unit = {

    val out = sum(List(1, 2, 3, 4))

    println(out)
  }

}
