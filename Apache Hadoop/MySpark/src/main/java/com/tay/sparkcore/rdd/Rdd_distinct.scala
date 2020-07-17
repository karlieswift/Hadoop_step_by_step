package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/12 9:21 
 *         ClassName: Rdd_distinct  
 * @version java "13.0.1"
 *          将数据集中重复的数据去重
 */
object Rdd_distinct {

  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,6,7,6,3,2)
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("rdd")
    val sc = new SparkContext(sparkConf)

    val datas: RDD[Int] = sc.makeRDD(list)


    //map(x => (x, null)).reduceByKey((x, _) => x, numPartitions).map(_._1)
    val value: RDD[Int] = datas.distinct(2)
    value.saveAsTextFile("outt")


//    def distinct(): RDD[T] = withScope {
//      distinct(partitions.length) // 分区
//    }

    println(value.collect().mkString(" "))

    sc.stop()



  }

}
