package com.tay.myArray

/**
 * @author karlieswift 
 *         date: 2020/7/1 8:59 
 *         ClassName: Array5  
 * @version java "13.0.1"
 */
object Array5 {

  def main(args: Array[String]): Unit = {
    val arr1 = Array(1,2,3)
    val arr2 = Array(4,5,6)

    val arr3 = Array.concat(arr1,arr2)
    println(arr3.mkString(" "))


    //指定范围的数组
    println(Array.range(0,10).mkString(" "))
    println(Array.range(0,10,2).mkString(" "))
    println(Array.range(1,10,2).mkString(" "))


    //填充 fill
    val arr4 = Array.fill[Int](6)(11)
    println(arr4.mkString(" "))
    val arr5 = Array.fill[String](6)("succeed")
    println(arr5.mkString(" "))
  }
}
