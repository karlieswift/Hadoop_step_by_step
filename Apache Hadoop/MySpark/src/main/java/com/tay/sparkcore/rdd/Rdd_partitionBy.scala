package com.tay.sparkcore.rdd

import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/13 14:56 
 *         ClassName: Rdd_partitionBy  
 * @version java "13.0.1"
 *          将数据按照指定Partitioner重新进行分区。Spark默认的分区器是HashPartitioner
 */
object Rdd_partitionBy {

  def main(args: Array[String]): Unit = {
   val list = List(("1","a"),("2","b"),("3","c"),("4","o"))

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
   val rdd: RDD[(String, String)] = sc.makeRDD(list,2)

    rdd.saveAsTextFile("output1")
    //p-1 1-2
    //p-2 3-4

    val value: RDD[(String, String)] = rdd.partitionBy(new HashPartitioner(3))
    value.saveAsTextFile("output")
    //p-1 1-4
    //p-2 2
    //p-3 3

    /**
     * 也可以自定义类分区，需要继承Partitioner
     */

    val list1: List[(String, Int)] = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))
     val value1: RDD[(String, Int)] = sc.makeRDD(list1,2)
    val value2: RDD[(String, Iterable[Int])] = value1.groupByKey(new Person(3))
    value2.saveAsTextFile("output2")


    sc.stop()
  }

}
class Person(n:Int) extends Partitioner {
  override def numPartitions: Int = n

  override def getPartition(key: Any): Int = {
    //采用模式匹配
    key match {
      case null => 0
      case "a" =>1
      case "b" =>2
      case _ =>3
    }
  }
}