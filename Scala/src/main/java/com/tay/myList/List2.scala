package com.tay.myList

/**
 * @author karlieswift 
 *         date: 2020/7/1 11:10 
 *         ClassName: List2  
 * @version java "13.0.1"
 *
 *          不可变list
 *          可变list:ListBuffer
 */
object List2 {


  def main(args: Array[String]): Unit = {
    val list1 = List(1, 2, 3)
    println(list1 eq Nil)


    val list2 = List()

    println(list2 == Nil)  //true
    println(list2 eq Nil)  //true
  }
}