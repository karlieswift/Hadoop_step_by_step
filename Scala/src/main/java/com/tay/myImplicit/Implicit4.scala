package com.tay.myImplicit

/**
 * @author karlieswift 
 *         date: 2020/7/6 9:58 
 *         ClassName: Implicit4  
 * @version java "13.0.1"
 */
object Implicit4 {


  def main(args: Array[String]): Unit = {

    var str="taylor"
    str.apply(0)
    println(str(0))
  }
}
