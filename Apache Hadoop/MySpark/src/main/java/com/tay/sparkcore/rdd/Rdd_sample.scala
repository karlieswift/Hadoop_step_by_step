package com.tay.sparkcore.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/12 8:37 
 *         ClassName: Rdd_sample  
 * @version java "13.0.1"
 *         sample： 根据指定的规则从数据集中抽取数据
 */
object Rdd_sample {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6,7,8,9,10)
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("rdd")
    val sc = new SparkContext(sparkConf)

    val datas: RDD[Int] = sc.makeRDD(list)

    // 抽取数据不放回（伯努利分布）
    // 伯努利算法：又叫0、1分布。例如扔硬币，要么正面，要么反面。
    // 具体实现：根据种子和随机算法算出一个数和第二个参数设置几率比较，小于第二个参数要，大于不要
    // 第一个参数：抽取的数据是否放回，false：不放回
    // 第二个参数：抽取的几率，范围在[0,1]之间,0：全不取；1：全取；
    // 第三个参数：随机数种子

    //fraction=0.5代表每个数据的抽取几率
//    val rdddata: RDD[Int] = datas.sample(false,0.5,6)




    // 抽取数据放回（泊松分布)
    // 第一个参数：抽取的数据是否放回，true：放回；false：不放回
    // 第二个参数：重复数据的几率，范围大于等于0.表示每一个元素被期望抽取到的次数
    // 第三个参数：随机数种子
    //fraction=0.5代表每个数据的抽取次数几率
    val rdddata: RDD[Int] = datas.sample(true,3)


    rdddata.collect().foreach(println)


    sc.stop()






  }

}
