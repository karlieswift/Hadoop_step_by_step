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
 *          UpdateStateByKey
 *          UpdateStateByKey原语用于记录历史记录，有时，我们需要在DStream中跨批次维护状态(例如流计算中累加wordcount)。
 *          针对这种情况，updateStateByKey()为我们提供了对一个状态变量的访问，用于键值对形式的DStream。给定一个由(键，事件)
 *          对构成的 DStream，并传递一个指定如何根据新的事件更新每个键对应状态的函数，它可以构建出一个新的 DStream，
 *          其内部数据为(键，状态) 对。
 *          updateStateByKey() 的结果会是一个新的DStream，其内部的RDD 序列是由每个时间区间对应的(键，状态)对组成的。
 *          updateStateByKey操作使得我们可以在用新信息进行更新时保持任意的状态。为使用这个功能，需要做下面两步：
 * 1. 定义状态，状态可以是一个任意的数据类型。
 * 2. 定义状态更新函数，用此函数阐明如何使用之前的状态和来自输入流的新值对状态进行更新。
 *          使用updateStateByKey需要对检查点目录进行配置，会使用检查点来保存状态。
 *
 *
 *          更新版的wordcount
 *
 *
 *          有状态updateStateByKey和无状态reduceByKey的区别
 *          有状态：会将本次启动的数据记录下，进行全部的统计，需要加入    ssc.checkpoint("cp")
 *          无状态：只会统计本周期的数据
 *
 */
object updateStateByKeyTest {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(3))
    //需要加入以下代码
    // java.lang.IllegalArgumentException: requirement failed: The checkpoint directory has not been set. Please set it by StreamingContext.checkpoint().
    ssc.checkpoint("cp")
    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop7", 9999)
    val mapDstream: DStream[(String, Int)] = value.flatMap(_.split(" ")).map((_, 1))

    //2-有状态操作：updateStateByKey可以将相同的key的value封装为seq集合
    mapDstream.updateStateByKey(
      (seq: Seq[Int], buffer: Option[Int]) => {
        val newcount = seq.sum + buffer.getOrElse(0)
        Option(newcount)
      }
    ).print()

    ssc.start()
    ssc.awaitTermination()


  }
}
