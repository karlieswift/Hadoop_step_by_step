package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/12 16:20 
 *         ClassName: Rdd_combineByKey  
 * @version java "13.0.1"
 *
 *          最通用的对key-value型rdd进行聚集操作的聚集函数（aggregation function）。
 *          类似于aggregate()，combineByKey()允许用户返回值的类型与输入不一致。
 *          小练习：将数据List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))
 *          求每个key的平均值
 */
object Rdd_combineByKey {

  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
   val rdd: RDD[(String, Int)] = sc.makeRDD(list,2)
    val sum: RDD[(String, (Int, Int))] = rdd.combineByKey(
    (_, 1),
      (x: (Int, Int), y) => {
        (x._1 + y, x._2 + 1)
      },
      (x: (Int, Int), y: (Int, Int)) => {
        (x._1 + y._1, x._2 + y._2)
      }
    )
    sum.mapValues(s=>{
        s._1 / s._2
    }).collect().foreach(println)

    /**
     * (b,95)
     * (a,91)
     */
    sc.stop()

  }
}
