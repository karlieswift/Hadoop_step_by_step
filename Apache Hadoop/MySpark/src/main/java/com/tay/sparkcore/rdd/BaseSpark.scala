package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/12 8:42 
 *         ClassName: BaseSpark  
 * @version java "13.0.1"
 */
object BaseSpark {
  var sc:SparkContext=_
  def my_initspark(): SparkContext ={
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("rdd")
    val sc = new SparkContext(sparkConf)
    sc
  }


  def my_sparkstop(): Unit ={
    sc.stop()
  }

}
