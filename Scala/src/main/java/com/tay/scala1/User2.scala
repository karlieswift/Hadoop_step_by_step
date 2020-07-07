package com.tay.scala1

/**
 *
 * output:
 * 无参构造器测试----每次都调用--------1
 * 无参构造器测试----每次都调用--------2
 * -------------------------------
 * 无参构造器测试----每次都调用--------1
 * 无参构造器测试----每次都调用--------2
 * 1-1
 * -------------------------------
 * 无参构造器测试----每次都调用--------1
 * 无参构造器测试----每次都调用--------2
 * 2-2
 *
 * @author karlieswift 
 *         date: 2020/6/29 17:20 
 *         ClassName: User2  
 * @version java "13.0.1"
 */
object User2 {

  def main(args: Array[String]): Unit = {

    new User2()
    println("-------------------------------")
    new User2("taylor")
    println("-------------------------------")
    new User2("taylor",22)
  }
}

class User2{
//  var name:String=_
  println("无参构造器测试----每次都调用--------1")

  def this(name:String){
    this()
    println("1-1")
  }

  def this(name:String,age:Int){
    this()
    println("2-2")
  }


  println("无参构造器测试----每次都调用--------2")
}