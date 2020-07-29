package com.tay.sparkstreaming.stop

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext, StreamingContextState}

/**
 * @author karlieswift 
 *         date: 2020/7/22 14:09
 *         ClassName: StopStreaming
 * @version java "13.0.1"
 *          流式任务需要7*24小时执行，但是有时涉及到升级代码需要主动停止程序，但是分布式程序，没办法做到一个个进程去杀死，
 *          所有配置优雅的关闭就显得至关重要了。
 *          使用外部文件系统来控制内部程序关闭。
 */
object StopStreaming {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(3))

    val value: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop7", 9996)

    val result: DStream[(String, Int)] = value.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

    result.print()

    ssc.start()

    /**
     * 一般不会停止
     * driver程序因为需要一直运行，所以也不需要进行停止，除非业务改变，升级等
     *     ssc.stop()
     * stop方法需要采用新的thread进行关闭
     */
    new Thread(new Runnable {
      override def run(): Unit = {
        //todo stop方法的调用不应该是线程刚启动就停止
        //todo 一般需要周期性的判断哪个时机是否要关闭
        while (true) {
          Thread.sleep(10000)
          //todo 关闭的方法一般不在程序内部进行完成,一般使用采用第三方的程序进行判断
          //todo 获取当前环境的状态
          val state: StreamingContextState = ssc.getState()
          if (state == StreamingContextState.ACTIVE) {

            //todo 1-强制关闭
            //            ssc.stop()
            //todo 2-优雅的关闭
            ssc.stop(true, true)
            System.exit(0)
          }
        }
      }
    }).start()


    ssc.awaitTermination()

  }
}
