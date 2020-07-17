package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 13:59 
 *         ClassName: Apachelog
 * @version java "13.0.1"
 *
 *         从服务器日志数据apache.log中获取用户请求URL资源路径
 *
 *         83.149.9.216 - - 17/05/2015:10:05:03 +0000 GET /presentations/logstash-monitorama-2013/images/kibana-search.png
 */
object Apachelog {


  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("tay")

    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.textFile("file/apache.log")
    val value: RDD[String] = rdd.map(line => {
      val datas: Array[String] = line.split(" ")
      datas(6)
    })



    value.collect().foreach(println)
    sc.stop()

  }
}
