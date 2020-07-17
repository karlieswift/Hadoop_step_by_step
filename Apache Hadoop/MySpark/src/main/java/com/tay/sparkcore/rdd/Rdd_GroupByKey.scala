package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/13 14:54 
 *         ClassName: Rdd_GroupByKey  
 * @version java "13.0.1"
 *
 *         groupBy && groupByKey
 */
object Rdd_GroupByKey {
  val list: List[(String, Int)] = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))

  val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
  val sc = new SparkContext(rddConf)
  val rdd: RDD[(String, Int)] = sc.makeRDD(list,2)
  val value: RDD[(String, Iterable[(String, Int)])] = rdd.groupBy(_._1)
  value.collect().foreach(println)
  /**
   * (b,CompactBuffer((b,95), (b,93), (b,98)))
   * (a,CompactBuffer((a,88), (a,91), (a,95)))
   */
  val value1: RDD[(String, Iterable[Int])] = rdd.groupByKey()
  value1.collect().foreach(println)
  /**
   * (b,CompactBuffer(95, 93, 98))
   * (a,CompactBuffer(88, 91, 95))
   */
  sc.stop()
}
