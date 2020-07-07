package com.tay.function

/**
 * @author karlieswift 
 *         date: 2020/6/26 14:04 
 *         ClassName: Test4  
 * @version java "13.0.1"
 */
object Test4 {

  def main(args: Array[String]): Unit = {

    def fun1()={
      "scala"
    }

    val a=fun1  //得到的是函数的过程
    val b=fun1 _  //传递的是函数体
    println(a)
    println(b)


  }


}
