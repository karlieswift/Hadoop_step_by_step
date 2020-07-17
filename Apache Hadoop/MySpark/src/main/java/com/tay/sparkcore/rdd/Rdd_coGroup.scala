package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/14 14:30 
 *         ClassName: Rdd_coGroup  
 * @version java "13.0.1"
 *          cogroup:
 *          在类型为(K,V)和(K,W)的RDD上调用，返回一个(K,(Iterable<V>,Iterable<W>))类型的RDD
 */
object Rdd_coGroup {

  def main(args: Array[String]): Unit = {


    val list=List( ("a",1),("a",2),("c",3),("e",66))
    val list1=List( ("c",33), ("a",11),("a",22),("d",1))

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(list)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(list1)

    rdd.cogroup(rdd1).collect().foreach(println)

    /**
     * output:
     * (a,(CompactBuffer(1, 2),CompactBuffer(11, 22)))
     * (c,(CompactBuffer(3),CompactBuffer(33)))
     * (d,(CompactBuffer(),CompactBuffer(1)))
     * (e,(CompactBuffer(66),CompactBuffer()))
     */

    sc.stop()
  }
}
