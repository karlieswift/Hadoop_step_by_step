package com.tay.myMap
import scala.collection.mutable
/**
 * @author karlieswift 
 *         date: 2020/7/1 13:58 
 *         ClassName: MutableMap1  
 * @version java "13.0.1"
 */
object MutableMap1 {


  def main(args: Array[String]): Unit = {
    val map1 = mutable.Map("a"->1,"b"->2)
    val map2 = map1+("c"->3)
    println(map2)
    val map4 = mutable.Map("a1"->1,"b1"->2)
    val map3=map4++=map2

    println(map4)
    println(map3==map4)
  }
}
