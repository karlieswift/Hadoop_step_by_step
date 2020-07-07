package com.tay.myList

import scala.collection.mutable.ListBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/1 11:21 
 *         ClassName: BufferList1  
 * @version java "13.0.1"
 *
 *         可变list
 */
object ListBuffer1 {


  def main(args: Array[String]): Unit = {
    val lb = ListBuffer[Int](1,2,3,4)
    println(lb)

    lb.append(5,6)
    println(lb)

    var re=lb.remove(lb.length-1)  //返回移除的元素
    println(re)
    println(lb)
    lb.remove(0,2) //去除从index=0,长度为2
    println(lb)
  }
}
