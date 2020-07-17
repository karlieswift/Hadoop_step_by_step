package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 14:20 
 *         ClassName: Rdd_Glom
 * @version java "13.0.1"
 *   glom:将同一个分区的数据直接转换为相同类型的内存数组进行处理，分区不变
 *  小功能：计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
 *
 */
object Rdd_Glom {

  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[Int] = sc.makeRDD(list,2)
    //方法一
    val rdd1: RDD[Array[Int]] = rdd.glom()
    val value: RDD[Int] = rdd1.map(
      arr => {
        arr.max
      }
    )
//    //方法二
//    val value: RDD[Int] = rdd.mapPartitions(list => {
//      List(list.max).iterator
//    })

    println(value.sum()) //9.0

    sc.stop()
  }
}
