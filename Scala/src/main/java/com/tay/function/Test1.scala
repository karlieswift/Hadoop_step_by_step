package com.tay.function

import com.tay.objectTest.{Person1, PersonDemo}
/**
 * @author karlieswift 
 *         date: 2020/6/24 14:54 
 *         ClassName: Test1  
 * @version java "13.0.1"
 */
object Test1 {

  def main(args: Array[String]): Unit = {



    new Te().fun()





    fun1(fun)
    def fun(a: Int): Int ={
      a*a-1
    }
    def fun1(f:(Int)=>Int): Unit ={
      println(f(3))
    }


  }



}

class Te extends Person1 {
  def fun(): Unit ={
    val d = new Person1()


  }
}
