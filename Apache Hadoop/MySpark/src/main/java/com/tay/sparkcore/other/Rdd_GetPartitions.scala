package com.tay.sparkcore.other

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 14:20 
 *         ClassName: Rdd_GetPartitions
 * @version java "13.0.1"
 *          获取第二个分区
 */
object Rdd_GetPartitions {

  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[Int] = sc.makeRDD(list,2)

    //获取分区数据的同时，获取每个数据所在的分区号码

    val value: RDD[Int] = rdd.mapPartitionsWithIndex(
      (index, iter) => {
        if (index == 1) {
          iter
        } else {
          //不能写null,z这里要返回一个空的迭代器（空的集合）
          //方法一
             Nil.iterator
//          List().iterator
          //方法二
//          iter.filter(it=>false)
        }
      }
    )

    value.collect().foreach(println)

    /**
     * 4
     * 5
     * 6
     *
     */



    sc.stop()





  }
}
