package com.tay.sparkcore.other

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/10 16:47 
 *         ClassName: Test  
 * @version java "13.0.1"
 */
object Test {

  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(list,2)
    val value: RDD[(String, Iterable[(String, Int)])] = rdd.groupBy(_._1)
    value.collect().foreach(println)

   val value1: RDD[(String, Iterable[Int])] = rdd.groupByKey()
    value1.collect().foreach(println)
    sc.stop()

  }
}
