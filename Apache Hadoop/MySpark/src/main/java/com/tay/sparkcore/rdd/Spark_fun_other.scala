package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/12 10:54 
 *         ClassName: Spark_fun_other  
 * @version java "13.0.1"
 *          union并集
 *          intersection交集
 *          subtract 差集
 *          zip 拉链:的一些要求1-数据量必须相同 2-分区数必须相同
 *
 *
 */
object Spark_fun_other {

  def main(args: Array[String]): Unit = {
    val list1 = List(3,4,5,6)
    val list2 = List(5,6,7,8)
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("rdd")
    val sc = new SparkContext(sparkConf)
    val datas1: RDD[Int] = sc.makeRDD(list1)
    val datas2: RDD[Int] = sc.makeRDD(list2)
    val list3 = List("5","4","taylor",2)
    val dd: RDD[Any] = sc.makeRDD(list3)

    /**
     * 集合：并集，交集，差集
     */
       println(datas1.union(datas2).collect().mkString(" "))//3 4 5 6 5 6 7 8
   println(datas1.intersection(datas2).collect().mkString(" "))//6 5
   println(datas1.subtract(datas2).collect().mkString(" "))//3 4
   println(datas1.zip(datas2).collect().mkString(" "))//(3,5) (4,6) (5,7) (6,8)
   println(datas1.zip(dd).collect().mkString(" "))//(3,5) (4,4) (5,taylor) (6,2)


//    value.saveAsTextFile("output")
    /**
     * 数据聚合
     */
//    println(datas1..collect().mkString(" "))



    sc.stop()
  }
}
