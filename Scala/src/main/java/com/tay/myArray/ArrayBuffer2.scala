package com.tay.myArray

import scala.collection.mutable.ArrayBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/1 8:43 
 *         ClassName: ArrayBuffer2
 * @version java "13.0.1"
 */
object ArrayBuffer2 {

  def main(args: Array[String]): Unit = {
    //创建可变数组   def this() = this(16) 默认16
    val a1 = new ArrayBuffer[String]()
//    println(a1.length)
    a1.append("a","b")
    println(a1.mkString(" "))
    a1.insert(0,"0","1")
    println(a1.mkString(" "))
    a1.update(2,"tay")
    println(a1.mkString(" "))

  }
}
