package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/12 15:41 
 *         ClassName: Rdd_aggregateByKey  
 * @version java "13.0.1"
 *
 *          1- aggregateByKey:将数据根据不同的规则进行分区内计算和分区间计算
 *          取出每个分区内相同key的最大值然后分区间相加
 *
 *          2-foldByKey: aggregateByKey就可以简化为foldByKey
 */
object Rdd_aggregateByKey {
  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    //取出每个分区内相同key的最大值然后分区间相加
    val list=List( ("a",1),("a",2),("c",3),("b",4),("c",5),("c",6))

    val value: RDD[(String, Int)] = sc.makeRDD(list,2)

    // 1. 第一个参数列表中的参数表示初始值
    // 2. 第二个参数列表中含有两个参数
    //    2.1 第一个参数表示分区内的计算规则
    //    2.2 第二个参数表示分区间的计算规则
//    val result: RDD[(String, Int)] = value.aggregateByKey(0)(
//      (x, y) => math.max(x, y),
//      (x, y) => x + y
//    )

    /**
     * output
     * (b,4)
     * (a,2)
     * (c,9)
     */


    /**
     * 当分区内计算规则和分区间计算规则相同时，aggregateByKey就可以简化为foldByKey
     *
     * //    val result: RDD[(String, Int)] = value.aggregateByKey(0)(
     * //     (x, y) => x + y,
     * //      (x, y) => x + y
     * //    )
     */

    val result: RDD[(String, Int)] = value.foldByKey(0)((x,y)=>x+y)
    /**
     * (b,4)
     * (a,3)
     * (c,14)
     */

    result.collect().foreach(println)
    sc.stop()
  }

}
