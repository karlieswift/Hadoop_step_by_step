package com.tay.scala1

/**
 * @author karlieswift 
 *         date: 2020/6/29 16:21 
 *         ClassName: Person  
 * @version java "13.0.1"
 */
object Person {

  def main(args: Array[String]): Unit = {

    val student = new Student("taylor", 21)

    println(student.toString)

    val teacher1 = new Teacher("Tom", 37, "121212")

    println(teacher1.toString)

    val teacher2 = new Teacher("karlie", 31)

    println(teacher2.toString)
  }
}


class Student() {
  println("无参构造器测试")  //只要声明对象，就是执行
  var sname: String = ""
  var sage: Int = _


  //辅助构造器，必须在代码块的第一行，显示的调用主构造器
  def this(name: String, age: Int) {
    this()
    this.sage = age
    this.sname = name

  }


  override def toString = s"Student($sname, $sage)"
}

class Teacher extends Student {
  var id: String = _

  //辅助构造器，必须在代码块的第一行，显示的调用主构造器
  def this(name: String, age: Int) {
    this()
    this.id = "000000"
    this.sage = age
    this.sname = name

  }
  //辅助构造器，必须在代码块的第一行，显示的调用主构造器
  def this(name: String, age: Int, id: String) {
    this()
    this.id = id
    this.sage = age
    this.sname = name

  }


  override def toString = s"Teacher($sname, $sage,$id)"
}