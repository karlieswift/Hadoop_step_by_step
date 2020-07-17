package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 14:20 
 *         ClassName: Rdd_MapPartitionsWithIndex  
 * @version java "13.0.1"
 *
 */
object Rdd_FlatMap {

  def main(args: Array[String]): Unit = {
    val list = List(List(1,2),3,List(4,5,6))
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
   val rdd: RDD[Any] = sc.makeRDD(list,2)

    rdd.flatMap(ls=>{
      ls match {
        case list:List[_]=>list
        case d=>List(d)
      }
    }).collect().foreach(println)



    sc.stop()





  }
}
