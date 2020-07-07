package com.tay.myList

import scala.collection.mutable.ListBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/1 11:28 
 *         ClassName: ListBuffer2  
 * @version java "13.0.1"
 */
object ListBuffer2 {

  def main(args: Array[String]): Unit = {

    val buffer1 = ListBuffer(1,2,3,4,"taylor")
    val buffer2 = ListBuffer(5,6)
//    println(buffer1)
//    println(buffer1.:+(5,6,"taylor"))
//    println(buffer1.:+(5))
//    println(buffer1.+:(5,6,"taylor"))
//    println(buffer1)

    //:+ 或者+: 是添加的一个整体
//    val buffer3 = buffer1.:+(5,6,"taylor")
//
//    println(buffer3)
//    println(buffer3.length)
//    println(buffer3(buffer3.length-1))

    val buffer4 = buffer1 ++ buffer2
    println(buffer4)

    val buffer5 = buffer1++=buffer2

    println(buffer1)
    println(buffer5)
    println(buffer1 eq buffer5)

    println(buffer1 eq buffer4) //fasle 比较的是地址
    println(buffer1 == buffer4)  //true 比较的是值

  }
}
