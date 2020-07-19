package com.tay.sparkcore.action

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/14 16:14 
 *         ClassName: Action_Save  
 * @version java "13.0.1"
 *
 *    将数据保存到不同格式的文件中
 *       1-保存成Text文件
 *     rdd.saveAsTextFile("output")
 *       2-序列化成对象保存到文件
 *     rdd.saveAsObjectFile("output1")
 *       3-保存成Sequencefile文件
 *     rdd.map((_,1)).saveAsSequenceFile("output2")
 */
object Action_Save {

  def main(args: Array[String]): Unit = {

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,3,4,5,6)
    val rdd: RDD[Int] = sc.makeRDD(list)

//    将数据保存到不同格式的文件中
    // 保存成Text文件
    rdd.saveAsTextFile("output")

    // 序列化成对象保存到文件
    rdd.saveAsObjectFile("output1")

    // 保存成Sequencefile文件
    rdd.map((_,1)).saveAsSequenceFile("output2")



    sc.stop()

  }

}
