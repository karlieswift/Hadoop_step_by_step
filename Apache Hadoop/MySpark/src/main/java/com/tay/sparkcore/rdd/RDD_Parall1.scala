package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/8 16:39 
 *         ClassName: RDD_Parall1
 * @version java "13.0.1"
 *
 *   集合的分区测试
 *
 *
 *
 * RDD分区主要用于分布式计算，可以将数据发送到不同的excutor执行计算
 * 并行度是整个集群执行时，同时执行的任务的个数:
 * 分区的个数与并行度没有直接关系，主要取决于cpu个数
 */
object RDD_Parall1 {
  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,3,4,5,6)
    /**
     * rdd的分区数可以改,默认分区为电脑的配置
     * LocalSchedulerBackend.scala文件下的代码：
     * override def defaultParallelism(): Int =
     *     scheduler.conf.getInt("spark.default.parallelism", totalCores)
     *
     *
     *      makeRDD方法的第二个参数是自定义分区的数量
     *      scheduler.conf.getInt("spark.default.parallelism", totalCores)
     *      scheduler.conf = SparkConf
     *      spark.default.parallelism ： Spark默认并行度
     *       如果从配置信息中获取不到spark.default.parallelism参数，那么会使用默认值totalCores
     *       totalCores表示当前环境配置的总核数
     *
     */
//    val numrdd: RDD[Int] = sc.makeRDD(list)
//    makeRDD方法的第二个参数是自定义分区的数量
    val numrdd: RDD[Int] = sc.makeRDD(list,3)
    //将rdd保存成分区文件
    val unit: Unit = numrdd.saveAsTextFile("output")

    //三个分区
    // part-00000 存1,2
    //part-00001 存3,4
    //part-00002 存5,6

    //    lines.collect().foreach(println)




    sc.stop()







  }
}
