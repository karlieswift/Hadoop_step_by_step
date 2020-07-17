package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/10 15:59 
 *         ClassName: Rdd_filter  
 * @version java "13.0.1"
 */
object Rdd_filter {

  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[Int] = sc.makeRDD(list)
    rdd.filter(ls=>{
      ls%2==1
    }).collect().foreach(println)



    sc.stop()
  }
}
