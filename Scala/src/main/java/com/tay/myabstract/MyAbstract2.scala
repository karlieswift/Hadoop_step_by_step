package com.tay.myabstract

/**
 * @author karlieswift 
 *         date: 2020/6/30 9:17 
 *         ClassName: MyAbstract2  
 * @version java "13.0.1"
 */
object MyAbstract2 {
  def main(args: Array[String]): Unit = {

    val child = new User2Child
    println(child.name)
    println(child.age)
    println(child.name1)
  }

  abstract class User2{
    var name:String
    var age:Int
    val name1:String="karlie"
  }
  class User2Child extends User2 {

    var name: String = "taylor" //可以不加override
    override var age: Int = 32  //可以不加override
    override val name1:String="karlie" //必须加override,且变量是val,var出错
  }
}
