package com.tay.sparkcore.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/19 9:42 
 *         ClassName: Persist1  
 * @version java "13.0.1"
 *          spark的持久化：将内存的数据保存到磁盘
 *          1)	RDD Cache缓存
 *          RDD通过Cache或者Persist方法将前面的计算结果缓存，默认情况下会把数据以缓存在JVM的堆内存中。
 *          但是并不是这两个方法被调用时立即缓存，而是触发后面的action算子时，该RDD将会被缓存在计算节点
 *          的内存中，并供后面重用。
 */
object Persist1 {


  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    //spark在执行作业时，由于存在血缘关系，rdd之间一旦出错，就会重新再次计算，

    // Spark在执行作业时，由于RDD的血缘关系的存在，一旦出现错误，那么计算需要从头开始。
    // 这种操作体现了框架处理的容错性，但是性能会受到极大的影响。
    // 1. 希望在保证容错性的基础上，还能够提高性能？
    //    如果增加缓存操作
    // 2. 重复计算时，能够提高性能？
    //    如果增加缓存操作

    // 如果使用缓存将RDD的计算结果进行保存。那么即使RDD中不保存数据，那么缓存中有数据
    // 就可以重复使用提高效率，一旦出现错误，也可以从缓存中获取中间处理的数据，也可以继续执行。

    // 一般情况下，会将比较重要的数据和执行时间比较长，重复使用数据进行缓存。



    val list: RDD[Int] = sc.makeRDD(List(1,2,3))

    val value: RDD[Int] = list.map(num => {
      println("map---------------")
      num
    })


    /**
     *  //将value结果加入缓存
     *  //不加缓存map---------------每次加载都会打印
     */
//    value.cache()

    /**
     * 如果想要存储到其他位置，可以用persist
     */
    value.persist(StorageLevel.DISK_ONLY)

    println(value.toDebugString)
    println(value.collect.mkString(","))
    println("------------------------------------------------")

    println(value.filter(_%2==1).collect.mkString(","))
    sc.stop()
  }
}
