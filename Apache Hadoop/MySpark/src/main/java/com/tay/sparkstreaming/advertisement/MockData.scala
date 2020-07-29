package com.tay.sparkstreaming.advertisement

import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.collection.mutable.ListBuffer
import scala.util.Random

/**
 * @author karlieswift 
 *         date: 2020/7/22 15:23 
 *         ClassName: MockData  
 * @version java "13.0.1"
 */
object MockData {
  def main(args: Array[String]): Unit = {
    //todo 生成数据--发送到kafka
    /**
     *  数据格式：时间戳 区域，城市 用户id 广告id
     *  使用kafka的producer 产生数据
     */

    val properties = new Properties()
    // 添加配置
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop7:9092")
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String,String](properties)


    while (true){
      for(data <- gendata()){
        producer.send(new ProducerRecord[String,String]("sparkstreaming",data))
        println(data)

      }
      Thread.sleep(2000)
    }


  }

  private def gendata()={
    val areas=List("HB","HN","HD")
    val citys=List("bj","sh","gz","xg")

    val listBuffer: ListBuffer[String] = ListBuffer[String]()
    for(i<- 1 to new Random().nextInt(50)){
      //数据格式：时间戳 区域，城市 用户id 广告id
      val time=System.currentTimeMillis()
      val area=areas(new Random().nextInt(3))
      val city=citys(new Random().nextInt(4))
      val userid=new Random().nextInt(6)+1
      val adid=new Random().nextInt(6)+1
      listBuffer.append(s"$time $area $city $userid $adid")
    }
   listBuffer
  }
}
