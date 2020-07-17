package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 8:43 
 *         ClassName: Rdd_GroupBy
 * @version java "13.0.1"
 */
object Rdd_GroupBy {


  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[Int] = sc.makeRDD(list)
    //指定规则分组
    val value: RDD[(Int, Iterable[Int])] = rdd.groupBy((num:Int) => {
      num % 3
    },3)
    value.collect().foreach(println)

    /**
     * (0,CompactBuffer(2, 4, 6))
     * (1,CompactBuffer(1, 3, 5))
     */

    sc.stop()





  }
}
