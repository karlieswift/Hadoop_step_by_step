package com.tay.mySet

/**
 * @author karlieswift 
 *         date: 2020/7/1 11:49 
 *         ClassName: Set1  
 * @version java "13.0.1"
 *
 *          不可变set
 */
object Set1 {

  def main(args: Array[String]): Unit = {


//    println(Set(1, 2, 3, 1)) //不重复的集合
//    println(Set(1, 2, 3, 1)+6) //不重复的集合
//    println(Set(1, 2, 3, 1)-1) //不重复的集合
   println(Set(1, 2, 3)+(5,4,6)) //不重复的集合


    val set1 = Set(1, 2, 3, 1)
    val set2 = Set(4,5,6)
    val set3=set1+4
    println(set3)
    val set4=set3+6
    println(set4)
    val set5=set4+5
    println(set5)
    println(set1++set2)

  }
}
