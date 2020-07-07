package com.tay.myImplicit

/**
 * @author karlieswift 
 *         date: 2020/7/6 9:05 
 *         ClassName: Implicit1  
 * @version java "13.0.1"
 *
 *          implicit transform 隐式转换
 */
object Implicit1 {

  def main(args: Array[String]): Unit = {

//    val byte:Byte=12
//    var i :Int=byte
//    val b:Byte=i.toByte
//    println(b)

    def test(d:Int): Unit ={
      println(d)
    }


    /**
     * 自定义添加转换规则，让编译器自动使用
     * double -->int 编译器会自动查找这个函数
     * @param d
     * @return
     */
    implicit def transform (d:Double): Int ={
      d.toInt
    }

    val person = new Person

    test(person.number)
  }


}
class Person{
  var number=1.1
}