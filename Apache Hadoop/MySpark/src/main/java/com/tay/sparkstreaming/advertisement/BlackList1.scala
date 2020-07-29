package com.tay.sparkstreaming.advertisement

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import java.text.SimpleDateFormat
import java.util.Date

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable.ListBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/22 16:02 
 *         ClassName: BlackList  
 * @version java "13.0.1"
 *          实现实时的动态黑名单机制：将每天对某个广告点击超过 100 次的用户拉黑。
 *          注：黑名单保存到MySQL中。
 */
object BlackList1 {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
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


    //todo 2-周期性 读取黑名单数据，将黑名单的用户进行数据过滤
    val filterrdd: DStream[AdsClicks] = value.transform(
      rdd => {
        val driverClass = "com.mysql.jdbc.Driver"
        val url = "jdbc:mysql://hadoop7:3306/test"
        val user = "root"
        val pw = "123456"
        Class.forName(driverClass)
        val connection: Connection = DriverManager.getConnection(url, user, pw)
        //过滤数据
        val strsql = "select userid from black_list"
        val statement: PreparedStatement = connection.prepareStatement(strsql)
        val res: ResultSet = statement.executeQuery()
        val listBuffer = new ListBuffer[String]()
        while (res.next()) {
          listBuffer.append(res.getString(1))
        }
        //关闭
        res.close()
        statement.close()
        connection.close()
        //过滤已经在黑名单的用户
        rdd.filter(
          u => {
            !listBuffer.contains(u.userid)
          }
        )

      }
    )

    //todo 3- 格式转化((time,userid,adid))-->((time,userid,adid),1)将数据进行统计
    val format = new SimpleDateFormat("yyyy-MM-dd")
    val Ddata: DStream[((String, String, String), Int)] = filterrdd.map {
      data => {
        val date = new Date(data.time.toLong)
        ((format.format(date), data.userid, data.adid), 1)
      }
    }
    //todo 类似于wordcount计算
    val newdata: DStream[((String, String, String), Int)] = Ddata.reduceByKey(_ + _)

    //todo 4-将统计的额结果和用户的历史数据合并，将符合的用户进行拉入黑名单
    newdata.foreachRDD(
      rdd => {
        rdd.foreach {
          case (k, v) => {
            println(new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(System.currentTimeMillis().toLong))
            val driverClass = "com.mysql.jdbc.Driver"
            val url = "jdbc:mysql://hadoop7:3306/test"
            val user = "root"
            val pw = "123456"
            Class.forName(driverClass)
            val connection: Connection = DriverManager.getConnection(url, user, pw)
            //todo 优化整个过程就是往user_ad_count和black_list插入数据,通过user_ad_count的count判断是否加入black_list
            //todo 可以通过on duplicate key语法 插入时更新
            val statement1: PreparedStatement = connection.prepareStatement(
              """
                |insert into user_ad_count (dt,userid,adid,count) values (?,?,?,?)
                |on duplicate key
                |update count=count+?
                |""".stripMargin)
            val statement2: PreparedStatement = connection.prepareStatement(
              """
                |insert into black_list (userid) values (?)
                |on duplicate key
                |update userid=?
                |""".stripMargin)

            val statement3: PreparedStatement = connection.prepareStatement(
              """
                |select count from user_ad_count
                |where dt= ? and userid= ? and adid= ? and count>100
                |""".stripMargin)
            //todo 查询count的值
            statement3.setString(1, k._1)
            statement3.setInt(2, k._2.toInt)
            statement3.setInt(3, k._3.toInt)
            val resultSet: ResultSet = statement3.executeQuery()

            //todo 先把数据插入到user_ad_count，如果存在更新数据
            statement1.setString(1, k._1)
            statement1.setInt(2, k._2.toInt)
            statement1.setInt(3, k._3.toInt)
            statement1.setInt(4, v)
            statement1.setInt(5, v)
            statement1.executeUpdate()

            //todo 上述查询的结果count存在，说明大于条件，加入black_list
            if (resultSet.next()) {
                statement2.setString(1, k._2)
                statement2.setString(2, k._2)
                statement2.executeUpdate()
                resultSet.close()

              }
            statement1.close()
            statement2.close()
            connection.close()

          }
        }
      }

        )


        ssc.start()
        ssc.awaitTermination()

      }

    case class AdsClicks(time: String, area: String, city: String, userid: String, adid: String)

  }
