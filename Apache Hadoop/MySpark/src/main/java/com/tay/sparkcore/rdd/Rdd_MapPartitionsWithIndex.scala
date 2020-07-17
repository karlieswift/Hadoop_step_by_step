package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/10 14:20 
 *         ClassName: Rdd_MapPartitionsWithIndex  
 * @version java "13.0.1"
 *          MapPartitionsWithIndex
 *              //获取分区数据的同时，获取每个数据所在的分区号码
 */
object Rdd_MapPartitionsWithIndex {

  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[Int] = sc.makeRDD(list,2)

    //获取分区数据的同时，获取每个数据所在的分区号码

    val value: RDD[(Int, Int)] = rdd.mapPartitionsWithIndex(
      (index, iter) => {
        iter.map(
          num => (index, num)
        )
      }
    )
    value.collect().foreach(println)

    /**
     * (0,1)
     * (0,2)
     * (0,3)
     * (1,4)
     * (1,5)
     * (1,6)
     *
     */



    sc.stop()





  }
}
