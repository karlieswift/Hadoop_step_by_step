package com.tay.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author karlieswift 
 *         date: 2020/7/21 15:37 
 *         ClassName: RddStreaming  
 * @version java "13.0.1"
 *          DStream上的操作与RDD的类似，分为Transformations（转换）和Output Operations（输出）两种
 *          ，此外转换操作中还有一些比较特殊的原语，
 *          如：updateStateByKey()、transform()以及各种Window相关的原语。
 */
object TransformStreaming {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(3))


    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop7", 9999)

    /**
     * value.map()与transform的区别
     * Transform允许DStream上执行任意的RDD-to-RDD函数。即使这些函数并没有在DStream的API中暴露出来，通过该函数可以方便的扩
     * 展Spark API。该函数每一批次调度一次。其实也就是对DStream中的RDD应用转换。
     */

    //todo 1-driver端的A 执行1次
    value.transform(   //transform可以理解为把rdd取出来用
      rdd => {
        //todo 2-driver端的B 执行n次
        rdd.map(_ * 2) //excutor端
      }
    ).print()
    ssc.start()
    ssc.awaitTermination()


  }
}
