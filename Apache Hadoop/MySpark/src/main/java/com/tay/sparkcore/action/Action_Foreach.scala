package com.tay.sparkcore.action

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/14 16:18 
 *         ClassName: Action_Foreach  
 * @version java "13.0.1"
 *
 *          foreach 分布式遍历RDD中的每一个元素，调用指定函数
 */
object Action_Foreach {

  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,3,4,5,6)
    val rdd: RDD[Int] = sc.makeRDD(list)

    //收集后打印
    rdd.collect().foreach(println)

    /**
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     */
    println("--------------------------------------")
    //分布式打印
    rdd.foreach(println)

    /**
     * 6
     * 1
     * 2
     * 5
     * 4
     * 3
     */
    sc.stop()

  }

}
