package com.tay.sparkcore.serial

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/14 21:54 
 *         ClassName: Closure
 * @version java "13.0.1"
 *
 *          Closure闭包
 *
  */
object Closure1 {

  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,3,4,5,6)
    val rdd: RDD[Int] = sc.makeRDD(list)


    // Driver端执行
    val user = new User
    rdd.foreach(
    ls=>{
      //excutor端
      //如果在excutor端使用driver的数据，需要进行序列化，否则无法传递
      //闭包检测是执行作业的前提条件。如果检测失败，那么作业根本无法执行
      println(user)
      }
    )



    sc.stop()
  }

}
class User extends Serializable {

}