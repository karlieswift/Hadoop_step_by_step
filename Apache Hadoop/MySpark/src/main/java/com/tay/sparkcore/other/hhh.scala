package com.tay.sparkcore.other

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/16 8:41 
 *         ClassName: hhh
 * @version java "13.0.1"
 *          包括从dag调度er中出现的异常的用户堆栈跟踪。
 */
object hhh {

  def main(args: Array[String]): Unit = {
    val tuple: (Int, Int) = (1,2)
    val ints = List(1,2)

    val functionToList: (Int => Nothing) => List[Nothing] = ints.map(_)
    List((1,2),(2,2))




  }
}
