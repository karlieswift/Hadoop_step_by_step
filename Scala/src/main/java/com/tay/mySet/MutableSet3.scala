package com.tay.mySet

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/1 12:09 
 *         ClassName: MutableSet2  
 * @version java "13.0.1"
 *          交集，
 */
object MutableSet3 {
  def main(args: Array[String]): Unit = {


    val set1: mutable.Set[Int] = mutable.Set(1,2,3)
    val set2: mutable.Set[Int] = mutable.Set(3,4,5,6)

    println(set1 & set2)  //Set(3) 交集
    println(set1 &~ set2)  // Set(1, 2) 差集
    println(set1 ++ set2) //并集 Set(1, 5, 2, 6, 3, 4)

  }
}
