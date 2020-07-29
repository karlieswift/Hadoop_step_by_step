package com.tay.sparkstreaming

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.log4j.receivers.net.SocketReceiver
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/21 15:37 
 *         ClassName: RddStreaming  
 * @version java "13.0.1"
 *
 *           自定义采集器
 */
object DefineStreaming {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(3))


    val value: ReceiverInputDStream[String] = ssc.receiverStream(new MyReciver("hadoop7", 9999))
    value.print()
    ssc.start()
    ssc.awaitTermination()


  }

  /**
   * 自定义采集器
   * 继承Receiver，定义泛型传入参数
   */
  class MyReciver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {


    private var socket: Socket = _

    def receive(): Unit = {
      socket = new Socket(host, port)
      val reader = new BufferedReader(new InputStreamReader(socket.getInputStream))
      //当receiver没有关闭并且输入数据不为空，则循环发送数据给Spark
      while (true) {
        val str: String = reader.readLine()
        store(str)
        Thread.sleep(2000)
      }
      reader.close()
    }

    override def onStart(): Unit = {
      new Thread(new Runnable {
        override def run(): Unit = {
          receive()
        }
      }).start()
    }
    override def onStop(): Unit = {
      if (socket != null) {
        socket.close()
        socket = null
      }
    }
  }

}
