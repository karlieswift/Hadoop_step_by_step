package com.tay.sparkcore.basewordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/7 15:36
 *         ClassName: WordCount1
 * @version java "13.0.1"
 *
 *
 *          spark的reduceByKey比scala的groupBy 和 mapValues效率高
 *
 *          output:
 *          (Spark,2)
 *          (Hello,3)
 *          (Scala,1)
 *          (Hadoop,2)
 */
object WordCount1 {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("tay")

    val lines = new SparkContext(sparkConf)

    val words: RDD[String] = lines.textFile("file/wordcount.txt")

    val value: RDD[String] = words.flatMap(_.split(" "))

    val wordmap : RDD[(String, Int)] = value.map(list => {
      (list, 1)
    })

    //spark的reduceByKey比scala的groupBy 和 mapValues效率高
    val result: RDD[(String, Int)] = wordmap.reduceByKey(_+_)

    result.collect().foreach(println)

  }
}
