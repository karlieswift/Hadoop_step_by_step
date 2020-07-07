package com.tay.myList

/**
 * @author karlieswift 
 *         date: 2020/7/1 11:05 
 *         ClassName: List1  
 * @version java "13.0.1"
 */
object List1 {


  def main(args: Array[String]): Unit = {

    val list1 = List(1,2,3,4)
    println(list1)
    //添加末尾一个元素
    println(list1:+5)
    //添加第一个元素
    println(list1.+:(0))
    //update
    println(list1.updated(0,6))
  }
}
