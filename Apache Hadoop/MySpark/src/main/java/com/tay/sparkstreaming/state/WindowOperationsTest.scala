package com.tay.sparkstreaming.state

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author karlieswift 
 *         date: 2020/7/22 10:36
 *         ClassName: StreamingWindowOperations
 * @version java "13.0.1"
 *          Window Operations可以设置窗口的大小和滑动窗口的间隔来动态的获取当前Steaming的允许状态。
 *          所有基于窗口的操作都需要两个参数，分别为窗口时长以及滑动步长。
 *          	窗口时长：计算内容的时间范围；
 *          	滑动步长：隔多久触发一次计算。
 */
object WindowOperationsTest {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(3))
    ssc.checkpoint("cp")
    //todo 窗口就是把数据的一部分作为整体使用，需要指定范围
    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop7",9999)
    val value1: DStream[(String, Int)] = value.flatMap(_.split(" ")).map((_,1))


    //窗口的大小
    //滑动的幅度
    val value2: DStream[(String, Int)] = value1.window(Seconds(6),Seconds(3)).reduceByKey(_+_)
//    val value2: DStream[(String, Int)] = value1.reduceByKeyAndWindow((x:Int,y:Int)=>{x+y},Seconds(6),Seconds(3))
    value2.print()
    ssc.start()
    ssc.awaitTermination()

  }
}
