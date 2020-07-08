package com.tay.sparkcore.basewordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/7 15:12
 *         ClassName: WordCount
 * @version java "13.0.1"
 *
 *          spark简单的单词统计
 *
 *          output:
 *          (Spark,2)
 *          (Hello,3)
 *          (Scala,1)
 *          (Hadoop,2)
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    //1-获取spark框架的环境
    //创建saprk的基础配置，根据配置穿件上下环境连接对象
    var sparkConf = new SparkConf().setMaster("local").setAppName("Sparkwordcount")
    val context = new SparkContext(sparkConf)
    //2-对象spark环境对数据进行处理（api）
    //    Source.fromFile()
    //2.1-文件读取 textFile()//将文件一行一行读取 返回字符串
    val lines: RDD[String] = context.textFile("file/wordcount.txt")
    //2.2-将一行的字符串，分开 扁平化
    val words: RDD[String] = lines.flatMap(line => line.split(" "))
    //2.3-单词分组
    val groupwords: RDD[(String, Iterable[String])] = words.groupBy(word => word)
    //2.4-统计  将Iterable计算count
    val result: RDD[(String, Int)] = groupwords.mapValues(list => list.size)
    result.collect().foreach(println)
    //3.关闭环境
    context.stop()
  }

}
