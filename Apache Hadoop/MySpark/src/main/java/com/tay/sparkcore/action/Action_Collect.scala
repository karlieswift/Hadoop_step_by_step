package com.tay.sparkcore.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/14 15:16 
 *         ClassName: Action_Collect
 * @version java "13.0.1"
 *
 *          行动算子:其实就是通过执行对应的方法，来运行作业
 */
object Action_Collect {


  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,3,4,5,6)
    val rdd: RDD[Int] = sc.makeRDD(list)


    //    在驱动程序中，以数组Array的形式返回数据集的所有元素
    //行动算子调用一次，作业就会执行一次
    rdd.collect().foreach(println)

    /**
     * runJob
     */


    sc.stop()


  }
}
