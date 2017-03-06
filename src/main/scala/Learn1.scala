/**
  * Created by yarenty on 06/03/2017.
  */
object IntMonoid {
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
