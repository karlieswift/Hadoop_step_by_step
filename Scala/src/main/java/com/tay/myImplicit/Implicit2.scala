package com.tay.myImplicit

/**
 * @author karlieswift 
 *         date: 2020/7/6 9:27 
 *         ClassName: Implicit2  
 * @version java "13.0.1"
 */
object Implicit2 {


  def main(args: Array[String]): Unit = {

    implicit  def transform (d:Double): Int ={
      d.toInt
    }
    var i :Int=1.1
    println(i)
  }
}
