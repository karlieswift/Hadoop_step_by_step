package com.tay.collectionMethod

import scala.io.Source

/**
 * @author karlieswift 
 *         date: 2020/7/3 10:48 
 *         ClassName: WordCount  
 * @version java "13.0.1"
 *
 *          topN函数，topN(path:String,n:Int):List[(String,Int)]
 *
 *
 *       Source.fromFile(path).getLines().toList.flatMap(_.split(" ")).map((_,1)).groupBy(_._1)
 *          .map((kv=>(kv._1,kv._2.size))).toList.sortBy(-_._2).take(3)
 *
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    val path="file/wordcount.txt"
    val  n=3
    val list1 = topN(path,n)
    for (elem <- list1) {
      println(elem)
    }
  }

  /**
   *
   * @param path  传入的路径 String
   * @param n  前n
   * @return
   */
  def topN(path:String,n:Int):List[(String,Int)]= {
    val list = Source.fromFile(path).getLines().toList.flatMap(_.split(" "))
    val map = list.map((_, 1)).groupBy(_._1)
    val map1 = map.map(kv => (kv._1, kv._2.size))
    val list1 = map1.toList.sortBy(-_._2).take(n)
    list1
  }
}
