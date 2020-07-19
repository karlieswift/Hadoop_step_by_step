package com.tay.sparkcore.other

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/10 16:29 
 *         ClassName: WordGroup  
 * @version java "13.0.1"
 *          将List("Hello", "hive", "hbase", "Hadoop")根据单词首写字母进行分组
 */
object WordGroup {

  def main(args: Array[String]): Unit = {
    val list = List("Hello", "hive", "hbase", "Hadoop")
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
   val rdd: RDD[String] = sc.makeRDD(list)
    rdd.groupBy(s=>{
      val c: Char = s.charAt(0)
      c
    }).collect().foreach(println)
    sc.stop()
  }
}
