package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift
 *         date: 2020/7/8 16:17
 *         ClassName: Rdd_Create1
 * @version java "13.0.1"
 *          创建RDD的方法四种方法
 *          1.1 内存中创建 : List(1,2,3,4)
 *          1.2 存储中创建 : File
 *          1.3 从RDD创建
 *          1.4 直接new
 *    前2种较常见
 *
 *
 *
 *          1.1集合创建RDD
 */
object Rdd_Create1 {
  def main(args: Array[String]): Unit = {
    //rdd创建
    //1-1集合内存创建：List(1,2,3,4,5,6)
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,3,4,5,6)

    //todo 将集合装换RDD 的方法
    /**
     * 方法一：
     *使用parallelize将集合装换RDD
     * parallelize:并行
     * scala集合里的List.par并行
     */
//    val numRDD: RDD[Int] = sc.parallelize(list)

    /**
     * 方法二
     * makeRDD：生成rdd对象
     * makeRDD的底层也是parallelize(seq, numSlices)
     */
    val numRDD: RDD[Int] = sc.makeRDD(list)
    numRDD.collect().foreach(println)

    sc.stop()





  }

}
