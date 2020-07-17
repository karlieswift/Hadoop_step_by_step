package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/13 15:18 
 *         ClassName: Rdd_ReduceByKey  
 * @version java "13.0.1"
 *          可以将数据按照相同的Key对Value进行聚合  reduceByKey
 */
 object Rdd_ReduceByKey{
  def main(args: Array[String]): Unit = {
    val list=List( ("a",1),("a",2),("c",3))

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(list)
    rdd.saveAsTextFile("output1")

    val value: RDD[(String, Int)] = rdd.reduceByKey(_+_,2)

    value.saveAsTextFile("output")

    /**
     * 分区 0：空
     * 分区1：
     * (a,3)
     * (c,3)
     */
    sc.stop()
  }
}
