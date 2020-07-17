package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 8:43 
 *         ClassName: Rdd_Map  
 * @version java "13.0.1"
 *
 *         MapPartition:用于分区数据的额统一计算，类似于批处理，效率高
 *         mapPartition算子在进行计算时，有时候会出现内存溢出
 *         因为必须全部数据计算完，才释放内存
 *
 */
object Rdd_MapPartitions {


  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[Int] = sc.makeRDD(list,2)
//    val value: RDD[Int] = rdd.mapPartitions(ls => {
//      ls.filter(_ % 2 == 1)
//    })
    val value: RDD[Int] = rdd.mapPartitions(iter => {
      List(iter.max).iterator
    })
    value


    value.collect().foreach(println)


    sc.stop()





  }
}
