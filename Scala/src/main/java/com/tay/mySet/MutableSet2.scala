package com.tay.mySet
import scala.collection.mutable
/**
 * @author karlieswift 
 *         date: 2020/7/1 12:09 
 *         ClassName: MutableSet2  
 * @version java "13.0.1"
 */
object MutableSet2 {
  def main(args: Array[String]): Unit = {


    val set1: mutable.Set[Int] = mutable.Set(1,2,3)
    val set2: mutable.Set[Int] = mutable.Set(4,5,6)
    val set3=mutable.Set(3,2,1)
    val set4=mutable.Set(3,2,1,"haha")
    val set5=mutable.Set(3,2,1,"h1aha")
    val set6=mutable.Set(3,2,1,"haha1")

    println(set4.mkString(" "))
    println(set5.mkString(" "))
    println(set6.mkString(" "))
    println(set1)
    println(set3)
    println(set1 ==set3)
  }
}
