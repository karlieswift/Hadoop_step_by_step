package com.tay.myArray
import scala.collection.mutable.ArrayBuffer
/**
 * @author karlieswift 
 *         date: 2020/7/1 8:43 
 *         ClassName: ArrayBuffer1  
 * @version java "13.0.1"
 */
object ArrayBuffer1 {

  def main(args: Array[String]): Unit = {
    val ab = new ArrayBuffer[Int]
    println(ab.length)

    ab.append(1,2,3)
    println(ab.mkString(" "))
    val ab1=ab
    println(ab.hashCode())
    println(ab1.hashCode())

    ab1.appendAll(ab)
    println(ab1.mkString(" "))
  }
}
