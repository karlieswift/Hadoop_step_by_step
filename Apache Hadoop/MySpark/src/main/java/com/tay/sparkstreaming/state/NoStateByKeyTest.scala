package com.tay.sparkstreaming.state

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author karlieswift 
 *         date: 2020/7/21 15:37
 *         ClassName: RddStreaming
 * @version java "13.0.1"
 *
 * 无状态
 *
 *          有状态updateStateByKey和无状态reduceByKey的区别
 *          有状态：会将本次启动的数据记录下，进行全部的统计，需要加入    ssc.checkpoint("cp")
 *          无状态：只会统计本周期的数据
 *
 */
object NoStateByKeyTest {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(3))
    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop7", 9999)
    val mapDstream: DStream[(String, Int)] = value.flatMap(_.split(" ")).map((_,1))
    //1-无状态
    mapDstream.reduceByKey(_+_).print()
    ssc.start()
    ssc.awaitTermination()


  }
}
