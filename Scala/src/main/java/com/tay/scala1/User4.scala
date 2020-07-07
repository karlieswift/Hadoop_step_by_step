package com.tay.scala1

/**
 *
 * output:
 *
 * 构造器测试----每次都调用
 * 1-1
 * 2-2
 * 2-21
 * 3-3
 * 4-4
 *
 * @author karlieswift 
 *         date: 2020/6/29 17:41 
 *         ClassName: User4  
 * @version java "13.0.1"
 *
 */
object User4 {

  def main(args: Array[String]): Unit = {
    new User4Child()
  }
}
class User4(name:String){
  println("构造器测试----每次都调用")
  println("1-1")
  def this() {
    this("taylor")
    println("2-2")
  }

  def this(a:Int) {
    this()
    println("2-21")
  }
}

//class User4Child extends User4(){
//
//}

class User4Child(name:String) extends User4(1){
  println("3-3")
  def this() {
    this("karlie")
    println("4-4")
  }
}