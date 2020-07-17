package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 8:43 
 *         ClassName: Rdd_Map  
 * @version java "13.0.1"
 */
object Rdd_Map {


  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val value: RDD[Int] = sc.makeRDD(list)
    val value1: RDD[(Int, Int)] = value.map(v=>(v,v+1))

    value1.collect().foreach(println)
    sc.stop()





  }
}
