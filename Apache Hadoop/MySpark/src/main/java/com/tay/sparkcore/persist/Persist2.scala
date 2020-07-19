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
object Persist2 {


  def main(args: Array[String]): Unit = {
    // TODO Spark - 持久化

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))

    val mapRDD = rdd.map(num=>{
      println("map....")
      num
    })

    // cache可以改变血缘关系，当执行出现错误的情况下，可以根据血缘从缓存中获取数据
    // 一般情况下，可以将数据计算结果保存到cache中，但是如果想要安全的，长久地保存起来
    // 那么我们一般是保存到分布式存储中HDFS
    mapRDD.cache()
    //mapRDD.persist(StorageLevel.DISK_ONLY)

    println(mapRDD.toDebugString)

    /**
     * (8) MapPartitionsRDD[1] at map at Persist2.scala:29 [Memory Deserialized 1x Replicated]
     * |  ParallelCollectionRDD[0] at makeRDD at Persist2.scala:27 [Memory Deserialized 1x Replicated]
     */
    println(mapRDD.collect.mkString(","))
    println("***************************")
    println(mapRDD.toDebugString)

    /**
     * (8) MapPartitionsRDD[1] at map at Persist2.scala:29 [Memory Deserialized 1x Replicated]
     * |       CachedPartitions: 8; MemorySize: 160.0 B; ExternalBlockStoreSize: 0.0 B; DiskSize: 0.0 B
     * |  ParallelCollectionRDD[0] at makeRDD at Persist2.scala:27 [Memory Deserialized 1x Replicated]
     */

    sc.stop()
  }
}
