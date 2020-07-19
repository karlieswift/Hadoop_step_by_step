package com.tay.sparkcore.other

import java.text.SimpleDateFormat

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/10 16:34 
 *         ClassName: GetVisit_Count  
 * @version java "13.0.1"
 *
 *          从服务器日志数据apache.log中获取每个时间段访问量。
 */
object GetVisit_Count {

  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("tay")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[String] = sc.textFile("file/apache.log")
    val value: RDD[String] = rdd.map(line => {
      val datas: Array[String] = line.split(" ")
      val str: String = datas(3).split(":")(1)
      str
    })
     value.groupBy(ls=>ls).mapValues(_.size).collect().foreach(println)

    sc.stop()

  }
}
