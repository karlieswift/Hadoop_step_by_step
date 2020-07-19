package com.tay.sparkcore.other

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/12 16:26 
 *         ClassName: Top3_Agent  
 * @version   java "13.0.1"
 *
 *            1)	数据准备
 *            agent.log：时间戳，省份，城市，用户，广告，中间字段使用空格分隔。
 *            1516609143867 6 7 64 16
 *            1516609143869 9 4 75 18
 *            1516609143869 1 7 87 12
 *            1516609143869 2 8 92 9
 *            2)	需求描述
 *            统计出每一个省份每个广告被点击数量排行的Top3
 */
object Top3_Agent {
  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)

    //读取数据
    val lines: RDD[String] = sc.textFile("file/agent.log")
    val rdd1: RDD[((String, String), Int)] = lines.map(
      lines => {
        val datas: Array[String] = lines.split(" ")
        ((datas(1), datas(4)), 1)
      }
    )
    val rdd2: RDD[((String, String), Int)] = rdd1.reduceByKey(_+_)

    val rdd3: RDD[(String, (String, Int))] = rdd2.map {
      case ((provence, ads), sum) => {
        (provence, (ads, sum))
      }
    }
    val value: RDD[(String, Iterable[(String, Int)])] = rdd3.groupByKey()
    val result: RDD[(String, List[(String, Int)])] = value.mapValues(
      v => {
        v.toList.sortWith(
          (left, rigth) => {
            left._2 > rigth._2
          }
        ).take(3)
      }
    )
    result.collect().foreach(println)

    /**
     * output
     * (4,List((12,25), (2,22), (16,22)))
     * (8,List((2,27), (20,23), (11,22)))
     * (6,List((16,23), (24,21), (22,20)))
     * (0,List((2,29), (24,25), (26,24)))
     * (2,List((6,24), (21,23), (29,20)))
     * (7,List((16,26), (26,25), (1,23)))
     * (5,List((14,26), (21,21), (12,21)))
     * (9,List((1,31), (28,21), (0,20)))
     * (3,List((14,28), (28,27), (22,25)))
     * (1,List((3,25), (6,23), (5,22)))
     */

    sc.stop()

  }

}
