package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift
 *         date: 2020/7/8 16:17
 *         ClassName: Rdd_Create2
 * @version java "13.0.1"
 *          创建RDD的方法四种方法
 *          1.1 内存中创建 : List(1,2,3,4)
 *          1.2 存储中创建 : File
 *          1.3 从RDD创建
 *          1.4 直接new
 *   前2种较常见
 *
 *         1.2内存创建RDD
 */
object Rdd_Create2 {
  def main(args: Array[String]): Unit = {

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    //1.2内存创建RDD
    //路径可以通过加*，也可以直接写目录
    val lines: RDD[String] = sc.textFile("file/")
//    val lines: RDD[String] = sc.textFile("file/*")

    lines.collect().foreach(println)

    sc.stop()





  }

}
