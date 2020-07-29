package com.tay.sparkstreaming

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/21 14:15 
 *         ClassName: StreamWordCount  
 * @version java "13.0.1"
 *          从数据的处理方式
 *          流式数据处理:来一条处理一条
 *          批量数据处理：一批一批处理：
 *          从数据的处理的延迟时间区分
 *          离线数据处理: 一般以天，或者小时
 *          实时数据处理：一般以ms s为单位
 *          SparkStreaming是处理批量数据，处理准实时数据
 *          Spark Streaming用于流式数据的处理。Spark Streaming支持的数据输入源很多，
 *          例如：Kafka、Flume、Twitter、ZeroMQ和简单的TCP套接字等等。数据输入后可以用Spark的高度抽象原语如
 *          ：map、reduce、join、window等进行运算。而结果也能保存在很多地方，如HDFS，数据库等。
 *
 *          sparkstreaming是准实时，微批次数据处理框架
 *
 *          	需求：使用netcat工具向9999端口不断的发送数据，通过SparkStreaming读取端口数据并统计不同单词出现的次数
 */
object StreamWordCount {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    //  todo 采集周期为3s
    val ssc = new StreamingContext(conf, Seconds(3))
    //执行计算 通过监控端口创建DStream，读进来的数据为一行行
    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop7", 9999)

    val words: DStream[String] = value.flatMap(
      lines => {
        lines.split(" ")
      }
    )
    val result: DStream[(String, Int)] = words.map((_, 1)).reduceByKey(_ + _)

    result.print()

    //启动
    ssc.start()
    ssc.awaitTermination()


  }


}
