package com.yarenty.streams

/**
  * The Sieve of Eratosthenes:
  * - all integers from 2 - first prime number
  * - eliminate all multiples of 2
  * - the first element of resulting is 3 - next prime number
  * - eliminate all multiples of 3
  * --> iterate forever - on each step first element is a prime number
  *
  * Created by yarenty on 21/03/17.
  */
object Eratosthenes {

  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))


  def main(args: Array[String]): Unit = {
    val nats = from(0)
    println("Naturals:")
    println(nats.take(10).toList)

    val m4s = nats map (_ * 4)
    println("Multiple 4s")
    println(m4s.take(10).toList)

    val primes = sieve(from(2))
    println("Primes:")
    println(primes.take(100).toList)

  }

}
