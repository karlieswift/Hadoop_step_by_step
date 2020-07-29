package com.tay.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.streaming.StreamingQueryListener
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/21 15:37 
 *         ClassName: RddStreaming  
 * @version java "13.0.1"
 */
object RddStreaming {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))



    val queue = mutable.Queue[RDD[Int]]()
    val rdd: InputDStream[Int] = ssc.queueStream(queue)

    val d: DStream[(Int, Int)] = rdd.map((_,1)).reduceByKey(_+_)

    d.print()


    for(i<- 1 to 5){
      queue +=ssc.sparkContext.makeRDD(1 to 10,10)
      Thread.sleep(2000)
    }
    ssc.start()
    ssc.awaitTermination()


  }
}
