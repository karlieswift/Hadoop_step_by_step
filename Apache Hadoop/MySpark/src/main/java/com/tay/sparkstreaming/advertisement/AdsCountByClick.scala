package com.tay.sparkstreaming.advertisement

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.text.SimpleDateFormat
import java.util.Date

import com.tay.sparkstreaming.advertisement.BlackList2.AdsClicks
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/24 8:29 
 *         ClassName: AdsCountByClick  
 * @version java "13.0.1"
 *          需求二：广告点击量实时统计
 *          描述：实时统计每天各地区各城市各广告的点击总流量，并将其存入MySQL。
 *          思路分析
 *          1）单个批次内对数据进行按照天维度的聚合统计;
 *          2）结合MySQL数据跟当前批次数据更新原有的数据。
 */
object AdsCountByClick {
  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("adclick")

    val ssc = new StreamingContext(conf, Seconds(3))
    // kakfa一般用于实时数据传输，所以在sparkstreaming传输
    //定义Kafka参数
    val kafkaPara: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop7:9092,hadoop8:9092,hadoop9:9092", //集群配置
      ConsumerConfig.GROUP_ID_CONFIG -> "tay", //消费者组的组名
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )
    //使用kakfa的工具类,来访问kafka,传递Topic和连接配置
    val kafkastreaming: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent, //位置策略 Use this only if your executors are on the same nodes as your Kafka brokers.
      ConsumerStrategies.Subscribe[String, String](Set("sparkstreaming"), kafkaPara) //kafka的配置
    )


    //todo 1-拿到的kafka的数据,封装类
    val value: DStream[AdsClicks] = kafkastreaming.map(
      lines => {
        val datas: Array[String] = lines.value().split(" ")
        //todo 1-读取kafka的数据，将数据转换为类进行操作
        (AdsClicks(datas(0), datas(1), datas(2), datas(3), datas(4)))
      }
    )

    val format = new SimpleDateFormat("yyyy-MM-dd")
    val result: DStream[((String, String, String, String), Int)] = value.map {
      rdd => {
        val date = new Date(rdd.time.toLong)
        ((format.format(date), rdd.area, rdd.city, rdd.adid), 1)
      }
    }.reduceByKey(_ + _)

    result.foreachRDD(
      rdd => {
        rdd.foreachPartition(
          datas => {
            val connection: Connection = JDBCUtils.getConnection
            val statement: PreparedStatement = connection.prepareStatement(
              """
                |insert into area_city_ad_count
                |(dt,area,city,adid,count) values(?,?,?,?,?)
                |on duplicate key
                |update count=count+?
                |""".stripMargin)

            datas.foreach {
              case (k, v) => {
                statement.setString(1, k._1)
                statement.setString(2, k._2)
                statement.setString(3, k._3)
                statement.setInt(4, k._4.toInt)
                statement.setInt(5, v)
                statement.setInt(6, v)
                statement.executeUpdate()

              }
            }
            statement.close()
            connection.close()
          }
        )
      }
    )


    ssc.start()
    ssc.awaitTermination()


  }

  case class AdsClicks(time: String, area: String, city: String, userid: String, adid: String)

}
