package com.tay.sparkcore.other

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 17:00 
 *         ClassName: GetLogsPath  
 * @version java "13.0.1"
 *          从服务器日志数据apache.log中获取2015年5月17日的请求路径
 * 93.114.45.13 - - 17/05/2015:10:05:14 +0000 GET /articles/dynamic-dns-with-dhcp/
 * 93.114.45.13 - - 17/05/2015:10:05:04 +0000 GET /reset.css
 * 93.114.45.13 - - 17/05/2015:10:05:45 +0000 GET /style2.css
 * 93.114.45.13 - - 17/05/2015:10:05:14 +0000 GET /favicon.ico
 * 93.114.45.13 - - 17/05/2015:10:05:17 +0000 GET /images/jordan-80.png
 * 93.114.45.13 - - 17/05/2015:10:05:21 +0000 GET /images/web/2009/banner.png
 * 66.249.73.135 - - 17/05/2015:10:05:40 +0000 GET /blog/tags/ipv6
 * 50.16.19.13 - - 17/05/2015:10:05:10 +0000 GET /blog/tags/puppet?flav=rss20
 * 66.249.73.185 - - 17/05/2015:10:05:37 +0000 GET /
 * 110.136.166.128 - - 17/05/2015:10:05:35 +0000 GET /projects/xdotool/
 */
object GetLogsPath {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("RDD")
    val sc = new SparkContext(conf)
    val rdd: RDD[String] = sc.textFile("file/apache.log")

    //   93.114.45.13 - - 17/05/2015:10:05:04 +0000 GET /reset.css
    rdd.filter(lines=>{
      val strings: Array[String] = lines.split(" ")
      strings(3).split(":")(0)=="17/05/2015"
    }).map(lines=>{
      val strings: Array[String] = lines.split(" ")
      strings(strings.length-1)
    }).collect().foreach(println)

    sc.stop()



  }
}
