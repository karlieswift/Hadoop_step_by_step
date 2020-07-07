package com.tay.collectionMethod

/**
 * @author karlieswift 
 *         date: 2020/7/3 9:00 
 *         ClassName: Map1  
 * @version java "13.0.1"
 */
object Map1 {

  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)
//    val list1 = List(List(1,2),List(3,4,5,6))6
    val list1 = List("q")

    val list2 = list1.flatMap(l=>l)
    println(list2)
//    println(list.map(_.toString))
//    println(list.map(_*2))
//    println(list.map((_,100)))
//    println(list.zipWithIndex)
    val set = Set(1,2,3)
    println(set.map(_*2))



    val map = Map(
      ("a", 1), ("b", 2), ("c", 3)
    )
    println(map.mapValues(_*2))//Map(a -> 2, b -> 4, c -> 6)
  }
}
