package com.tay.mySet

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/1 12:02 
 *         ClassName: MutableSet1  
 * @version java "13.0.1"
 *
 *
 *         可变set
 */
object MutableSet1 {

  def main(args: Array[String]): Unit = {
    val set1 = mutable.Set(1,2,3)
    val set2 = mutable.Set(4,5,6)

    set1.add(4)
//    set1.update(3,true) //update删除元素，true不更新set,false 更新set
    set1.remove(3)  //删除元素3
    println(set1.mkString(" "))
  }
}
