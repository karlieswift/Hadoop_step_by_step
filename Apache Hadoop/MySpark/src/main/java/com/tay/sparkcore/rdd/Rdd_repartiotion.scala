package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/12 11:01 
 *         ClassName: Rdd_repartiotion  
 * @version java "13.0.1"
 *          repartition：增加分区
 *          coalesce:减少分区
 */
object Rdd_repartiotion {

  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3, 4, 5, 6)

    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("rdd")
    val sc = new SparkContext(sparkConf)
    val datas1: RDD[Int] = sc.makeRDD(list1,3)

    /**
     * def repartition(numPartitions: Int)(implicit ord: Ordering[T] = null): RDD[T] = withScope {
     * coalesce(numPartitions, shuffle = true)
     * }
     */
 val value1: RDD[Int] = datas1.repartition(4)
 val value2: RDD[Int] = datas1.coalesce(2,false)

//
        value1.saveAsTextFile("output1")
        value2.saveAsTextFile("output2")
    datas1.saveAsTextFile("output3")


    sc.stop()
  }
}
