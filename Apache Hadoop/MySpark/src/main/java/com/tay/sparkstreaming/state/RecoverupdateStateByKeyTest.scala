package com.tay.sparkstreaming.state

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
 *
 *
 *          有状态updateStateByKey和无状态reduceByKey的区别
 *          有状态：会将本次启动的数据记录下，进行全部的统计，需要加入    ssc.checkpoint("cp")
 *          无状态：只会统计本周期的数据
 *
 *          3-如果系统异常后，如果想要重启后需要从原来的检查点重新恢复数据
 *          ：StreamingContext.getOrCreate
 *
 */
object RecoverupdateStateByKeyTest {

  def main(args: Array[String]): Unit = {


    //3-如果系统异常后，重启后需要从原来的检查点重写恢复数据
    // 所谓的有状态应该将每一次采集周期的统计结果进行保存，和后面的统计结果进行合并
    val ssc: StreamingContext = StreamingContext.getOrCreate("cp", () => {
      val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
      val context = new StreamingContext(conf, Seconds(3))
      context.checkpoint("cp")
      val value: ReceiverInputDStream[String] = context.socketTextStream("hadoop7", 9999)
      val mapDstream: DStream[(String, Int)] = value.flatMap(_.split(" ")).map((_, 1))
      mapDstream.updateStateByKey(
        (seq: Seq[Int], buffer: Option[Int]) => {
          val newcount = seq.sum + buffer.getOrElse(0)
          Option(newcount)
        }
      ).print()
      context
    }
    )

    ssc.start()
    ssc.awaitTermination()


  }
}
