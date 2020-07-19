package com.tay.sparkcore.broadcast

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/18 19:33 
 *         ClassName: Broadcast1  
 * @version java "13.0.1"
 *          广播变量：
 *
 *          广播变量用来高效分发较大的对象。向所有工作节点发送一个较大的只读值，以供一个或多个Spark操作使用。
 *          比如，如果你的应用需要向所有节点发送一个较大的只读查询表，
 *          广播变量用起来都很顺手。在多个并行操作中使用同一个变量，但是 Spark会为每个任务分别发送。
 */
object Broadcast1 {


  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("d", 4)), 4)
    val list = List(("a", 4), ("b", 5), ("c", 6), ("d", 7))

    val map: Map[String, Int] = list.toMap
    // 声明广播变量
    val bc: Broadcast[Map[String, Int]] = sc.broadcast(map)
    val result: RDD[(String, (Int, Int))] = rdd1.map {
      case (x, y) => {

        val i: Int = bc.value.getOrElse(x, 0)

        (x, (i, y))
      }
    }



    result.collect().foreach(println)




  }
}
