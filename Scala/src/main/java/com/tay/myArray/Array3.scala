package com.tay.myArray

/**
 * @author karlieswift 
 *         date: 2020/6/30 17:23 
 *         ClassName: Array3  
 * @version java "13.0.1"
 *
 *          var array3=array1.:+(3)
 *          var array4=array1.+:(0)
 */
object Array3 {


  def main(args: Array[String]): Unit = {
    val array1=Array(1,2)
    val array2=Array(6,7,8,9)
    var array3=array1.:+(3)
    println(array3.mkString(" "))

    val array4=array1.+:(0)
    println(array4.mkString(" "))

    val newArray2: Array[Int] = array1 ++ array2
    println(newArray2.mkString(" "))
    println( )


  }
}
