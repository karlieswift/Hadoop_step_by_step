package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/12 10:41 
 *         ClassName: Rdd_sort  
 * @version java "13.0.1"
 */
object Rdd_sort {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,6,7,6,3,2)
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("rdd")
    val sc = new SparkContext(sparkConf)

    val datas: RDD[Int] = sc.makeRDD(list,2)

    val value: RDD[Int] = datas.sortBy(num => {
      -num
    },numPartitions = 3)
    value.collect().foreach(println)

    value.saveAsTextFile("output")


    sc.stop()
  }
}
