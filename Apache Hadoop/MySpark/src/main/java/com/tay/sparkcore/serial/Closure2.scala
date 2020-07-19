package com.tay.sparkcore.serial

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/14 21:54 
 *         ClassName: Closure
 * @version java "13.0.1"
 *
 *          2)	序列化方法和属性
 *          从计算的角度, 算子以外的代码都是在Driver端执行, 算子里面的代码都是在Executor端执行，看如下代码：
 *
  */
object Closure2 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3.创建一个RDD
    val rdd: RDD[String] = sc.makeRDD(Array("hello world", "hello spark", "hive", "taylor"))

    //3.1创建一个Search对象
    val search = new Search("hello")

    //3.2 函数传递，打印：ERROR Task not serializable
    search.getMatch1(rdd).collect().foreach(println)

    //3.3 属性传递，打印：ERROR Task not serializable
    search.getMatch2(rdd).collect().foreach(println)

    //4.关闭连接
    sc.stop()
  }
}

class Search(query:String) extends Serializable {

  def isMatch(s: String): Boolean = {
    s.contains(query)
  }

  // 函数序列化案例
  def getMatch1 (rdd: RDD[String]): RDD[String] = {
    //rdd.filter(this.isMatch)
    rdd.filter(isMatch)
  }

  // 属性序列化案例
  def getMatch2(rdd: RDD[String]): RDD[String] = {
    //rdd.filter(x => x.contains(this.query))
    rdd.filter(x => x.contains(query))
    //val q = query
    //rdd.filter(x => x.contains(q))
  }
}
