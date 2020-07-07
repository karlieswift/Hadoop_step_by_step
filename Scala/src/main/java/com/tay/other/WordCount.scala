package com.tay.other

import scala.io.Source

/**
 * @author karlieswift 
 *         date: 2020/7/3 8:18 
 *         ClassName: WordCount  
 * @version java "13.0.1"
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    val source = Source.fromFile("file/wordcount.txt").getLines().toList.flatMap(_.split(" "))
    val list = source.map(x=>(x,1))
    val map1 = list.groupBy(_._1)
    val map = map1.map(kv=>(kv._1,kv._2.size)).toList.sortBy(kv=> -kv._2).take(3)
    val iterator = map.iterator
    while(iterator.hasNext){
      println(iterator.next())
    }
  }

}
