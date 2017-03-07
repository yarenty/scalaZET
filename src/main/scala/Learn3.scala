/**
  * Created by yarenty on 06/03/2017.
  */
object Learn3 {


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


  trait MonoidOp[A] {
    val F: Monoid[A]
    val value: A
    def |+|(a2: A) = F.mappend(value, a2)
  }


  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
    val F = implicitly[Monoid[A]]
    val value = a
  }


  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }


  def plus[A: Monoid](a: A, b: A): A = implicitly[Monoid[A]].mappend(a, b)

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

    println(plus(3,4))

    println(3 |+| 4)
    println("a" |+| "b")

  }

}
