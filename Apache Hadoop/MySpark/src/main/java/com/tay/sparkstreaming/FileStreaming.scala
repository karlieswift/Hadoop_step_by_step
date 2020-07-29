package com.tay.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/21 15:37 
 *         ClassName: RddStreaming  
 * @version java "13.0.1"
 */
object FileStreaming {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))

    val value: DStream[String] = ssc.textFileStream("infile")
    value.print()
    ssc.start()
    ssc.awaitTermination()



  }
}
