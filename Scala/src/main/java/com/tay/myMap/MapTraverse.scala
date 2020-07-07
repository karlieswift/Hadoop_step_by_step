package com.tay.myMap

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/1 14:10 
 *         ClassName: MapTravers  
 * @version java "13.0.1"
 *
 *          遍历
 */
object MapTraverse {

  def main(args: Array[String]): Unit = {
    val map = mutable.Map("a"->1,"b"->2)

    println(map) //Map(b -> 2, a -> 1)
    val iterator = map.keysIterator

    while(iterator.hasNext){
      println(map.get(iterator.next()).get)
    }
  }
}
