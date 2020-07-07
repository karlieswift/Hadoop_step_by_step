package com.tay.function


/**
 * 最简原则
 * @author karlieswift 
 *         date: 2020/6/26 12:48 
 *         ClassName: Test3  
 * @version java "13.0.1"
 */
object Test3 {

  def main(args: Array[String]): Unit = {

    /**
     * 省略return
     */
    def fun1(age: Int): Int ={
//      return age
      age
    }
//    println(fun1(22))

    /**
     * 省略花括号
     */
    def fun2(string: String): String=string
    def fun3(string: String) =string
//    println(fun2("haha1"))
//    println(fun3("haha2"))
    def fun4()="kk1"
    def fun5="kk2"
//    println(fun4())
//    println(fun5)



  }


}
