package com.tay.function

/**
 * 函数作为参数
 * @author karlieswift 
 *         date: 2020/6/26 14:09 
 *         ClassName: Test5  
 * @version java "13.0.1"
 */
object Test5 {

  def main(args: Array[String]): Unit = {

    def fun(f:(Int,Int)=>Int): Int ={
      f(10,100)
    }

    def fun1(a: Int,b: Int): Int ={
      a*b
    }

    println(fun(fun1))
  }


}

