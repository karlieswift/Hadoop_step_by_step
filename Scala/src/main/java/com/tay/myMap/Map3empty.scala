package com.tay.myMap

/**
 * @author karlieswift 
 *         date: 2020/7/1 12:33 
 *         ClassName: Map3empty
 * @version java "13.0.1"
 */
object Map3empty {


  def main(args: Array[String]): Unit = {
    val map1 = Map("a"->1,"b"->2)
    val map2 = Map("c"->3,"d"->4)

    //创建空的集合
    //TODO 当为空时，地址相同
    val empty1 = Map.empty
    val map3 = Map()
    println(empty1 == map3) //true
    println(empty1 eq map3) //true
    println(empty1.hashCode()+"  "+map3.hashCode())  // -1609326920  -1609326920  hashcode一样

  }
}
