/**
  * Created by yarenty on 06/03/2017.
  */
object Learn2_foldLeft {


  trait FoldLeft[F[_]] {
    def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
  }
  object FoldLeft {
    implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
    }
  }

  trait Monoid[A] {
    def mappend(a1: A, a2: A): A

    def mzero: A
  }

  object Monoid {
    implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
      def mappend(a: Int, b: Int): Int = a + b

      def mzero: Int = 0
    }
    implicit val StringMonoid: Monoid[String] = new Monoid[String] {
      def mappend(a: String, b: String): String = a + b

      def mzero: String = ""
    }
  }

  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }



  val multiMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a * b

    def mzero: Int = 1
  }

  def main(args: Array[String]): Unit = {


    val out = sum(List(1, 2, 3, 4))

    println(out)

    val o2 = sum(List("a", "b", "c"))
    println(o2)

    val o3 = sum(List(1, 2, 3, 4))( FoldLeft.FoldLeftList, multiMonoid)
    println(o3)

  }

}
