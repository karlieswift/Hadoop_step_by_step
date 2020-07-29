package com.tay.sparkstreaming.advertisement

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

/**
 * @author karlieswift 
 *         date: 2020/7/22 15:53 
 *         ClassName: ReceiverData  
 * @version java "13.0.1"
 */
object ReceiverData {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(2))

    // kakfa一般用于实时数据传输，所以在sparkstreaming传输

    //定义Kafka参数
    val kafkaPara: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop7:9092,hadoop8:9092,hadoop9:9092", //集群配置
      ConsumerConfig.GROUP_ID_CONFIG -> "tay",  //消费者组的组名
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )

    //使用kakfa的工具类,来访问kafka,传递Topic和连接配置
    val kafkastreaming: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent, //位置策略 Use this only if your executors are on the same nodes as your Kafka brokers.
      ConsumerStrategies.Subscribe[String, String](Set("sparkstreaming"), kafkaPara) //kafka的配置
    )

    //kafka传输的数据是k-v键值对，所以这里InputDStream[ConsumerRecord[String, String]]是[string，string]
    kafkastreaming.map(_.value()).print()



    ssc.start()
    ssc.awaitTermination()


  }

}
