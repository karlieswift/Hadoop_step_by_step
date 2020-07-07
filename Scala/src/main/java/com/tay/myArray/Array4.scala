package com.tay.myArray

/**
 * 多维数组
 * @author karlieswift 
 *         date: 2020/7/1 8:53 
 *         ClassName: Array4  
 * @version java "13.0.1"
 */
object Array4 {

  def main(args: Array[String]): Unit = {
    var matrix=Array.ofDim[Int](2,3)
    for (elem <- matrix) {
      println(elem.mkString(" "))
    }
  }
}
