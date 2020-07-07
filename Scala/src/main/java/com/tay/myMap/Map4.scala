package com.tay.myMap

/**
 * @author karlieswift 
 *         date: 2020/7/1 12:38 
 *         ClassName: Map4  
 * @version java "13.0.1"
 */
object Map4 {


  def main(args: Array[String]): Unit = {
    val map1 = Map("a"->1,"b"->List.range(0,10))


//    println(map1.apply("a")) // 1
//    println(map1("a")) // 1
////    println(map1("tay")) //java.util.NoSuchElementException: key not found: tay
//
//    println(map1.get("b")) //Some(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
//    println(map1.get("tay")) //None
//   println(map1.getOrElse("tay","haha"))


    val option1 = map1.get("a")
    if(!option1.isEmpty){
      println(option1.get)
    }
    val option2 = map1.get("a1")
//    println(option2)
    if(!option2.isEmpty){
      println(option2.get)
    }
  }
}
