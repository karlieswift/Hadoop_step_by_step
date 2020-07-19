package com.tay.sparkcore.accumulator

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator

/**
 * @author karlieswift 
 *         date: 2020/7/16 12:01 
 *         ClassName: SystemAccumulator  
 * @version java "13.0.1"
 *        累加器
 *              实现原理:
 *          累加器用来把Executor端变量信息聚合到Driver端。在Driver程序中定义的变量，
 *          在Executor端的每个Task都会得到这个变量的一份新的副本，每个task更新这些副本的值后，传回Driver端进行merge。
 *
 *
 *          1-系统累加器
 */
object SystemAccumulator {

  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,3,4,5,6)
    val rdd: RDD[Int] = sc.makeRDD(list)
    //声明累加器
    val sum: LongAccumulator = sc.longAccumulator("SystemAccumulator")

    println("sum="+sum)//sum=LongAccumulator(id: 0, name: Some(SystemAccumulator), value: 0)
    rdd.foreach(
      number=>{
        //使用累加器
        sum.add(number)
      }
    )

    println("sum="+sum)//sum=LongAccumulator(id: 0, name: Some(SystemAccumulator), value: 21)
    //打印sum的值
    println(sum.value)//21
    sc.stop()
  }

}
