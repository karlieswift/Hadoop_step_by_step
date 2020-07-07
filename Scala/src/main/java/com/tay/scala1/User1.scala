package com.tay.scala1

/**
 * @author karlieswift 
 *         date: 2020/6/29 17:17 
 *         ClassName: User1  
 * @version java "13.0.1"
 */
object User1 {

  def main(args: Array[String]): Unit = {


    new User1()
    println("-----------------------------------------------")
    new User1().User1()
  }
}

class User1{
  println("无参构造器测试----每次都调用")

  def User1(): Unit ={
    println("这是函数，不是构造器")
  }
}