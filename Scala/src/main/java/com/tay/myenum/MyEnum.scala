package com.tay.myenum

/**
 * @author karlieswift 
 *         date: 2020/6/30 15:22 
 *         ClassName: MyEnum  
 * @version java "13.0.1"
 */
object MyEnum {

print(1)
  def main(args: Array[String]): Unit = {
    println("2")
    println(Color.PINK)
    println(Color.PINK.id)
  }
  print(3)
}
object Color extends Enumeration{
  val RED=Value(1,"red")
  val PINK=Value(2,"pink")
}