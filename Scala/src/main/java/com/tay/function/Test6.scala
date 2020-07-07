package com.tay.function

/**
 * 函数作为返回值
 * @author karlieswift 
 *         date: 2020/6/26 14:16 
 *         ClassName: Test6  
 * @version java "13.0.1"
 */
object Test6 {

  def main(args: Array[String]): Unit = {

    def fun()={
      fun1 _
    }
    def fun1(a:Int,b:Int): Int ={
      a+b
    }

    println(fun()(1,2))
  }


}
