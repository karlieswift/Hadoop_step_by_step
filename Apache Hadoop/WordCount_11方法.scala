package com.tay.sparkcore.wordcount

import scala.collection.mutable
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

import scala.collection.mutable.ListBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/16 16:51 
 *         ClassName: WordCount_11  
 * @version java "13.0.1"
 *
 *          11种wordcount
 */
object WordCount_11 {

  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)

    // 方法1 groupBy
    val rdd1: RDD[String] = sc.makeRDD(List("hello", "taylor", "taylor"))
    rdd1.groupBy(word => word).mapValues(_.size).collect().foreach(println)

    // 方法2 groupByKey
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("hello", 22), ("taylor", 10), ("taylor", 12)))
    println(rdd2.groupByKey().mapValues(_.sum).collect().mkString(" "))

    // 方法3 reduceBuKey
    val rdd3: RDD[(String, Int)] = sc.makeRDD(List(("hello", 22), ("taylor", 10), ("taylor", 12)))
    println(rdd3.reduceByKey(_ + _).collect().mkString(" "))


    // 方法4 aggregateByKey
    val rdd4: RDD[(String, Int)] = sc.makeRDD(List(("hello", 22), ("taylor", 10), ("taylor", 12)))
    println(rdd4.aggregateByKey(0)(
      (m1, m2) => {
        m1 + m2
      },
      (m1, m2) => {
        m1 + m2
      }
    ).collect().mkString(" "))

    // 方法5 foldByKey
    val rdd5: RDD[(String, Int)] = sc.makeRDD(List(("hello", 22), ("taylor", 10), ("taylor", 12)))
    println(rdd5.foldByKey(0)(
      (m1, m2) => {
        m1 + m2
      }
    ).collect().mkString(" "))

    // 方法6 combineByKey
    val rdd6: RDD[(String, Int)] = sc.makeRDD(List(("hello", 22), ("taylor", 10), ("taylor", 12)))
    println(rdd6.combineByKey(
      words => words,
      (m1: Int, m2: Int) => {
        m1 + m2
      },
      (m1: Int, m2: Int) => {
        m1 + m2
      }
    ).collect().mkString(" "))

    // 方法7 countByKey
    val rdd7: RDD[(String, Int)] = sc.makeRDD(List(("hello", 1), ("hello", 1), ("taylor", 1)))
    println(rdd7.countByKey().mkString(" "))


    // 方法8 countByValue
    val rdd8: RDD[(String, Int)] = sc.makeRDD(List(("hello", 1), ("hello", 1), ("taylor", 1)))
    println(rdd8.countByValue().map(m => (m._1._1, m._2)).mkString(" "))
    //countByValue 对于List(("hello",1),("hello",2),("taylor",1))也可以进行统计，如下
    val rdd81: RDD[(String, Int)] = sc.makeRDD(List(("hello", 1), ("hello", 22), ("taylor", 1)))
    println(rdd81.map(word => {
      val buffer: ListBuffer[(String, Int)] = ListBuffer[(String, Int)]()
      val n: Int = word._2
      for (i <- 1 to n) {
        {
          //("hello",22)进行拆开
          buffer.append((word._1, 1))
        }
      }
      buffer.toList
    }).flatMap(l => l).countByValue().map(m => (m._1._1, m._2)).mkString(" "))


    // 方法9 aggregate
    val rdd9: RDD[String] = sc.makeRDD(List("hello", "taylor", "taylor"))
    val map = mutable.Map[String, Int]()
    println(rdd9.aggregate(map)(
      (map1, word) => {
        val i: Int = map1.getOrElse(word, 0)
        map1.update(word, i + 1)
        map1
      },
      (map1, map2) => {
        map1.foldLeft(map2)(
          (m1, m2) => {
            val word: String = m2._1
            val count: Int = m2._2
            val i: Int = m1.getOrElse(word, 0)
            m1.update(word, i + count)
            m1
          }
        )
      }
    ).mkString(" "))

    // 方法10 fold
    val rdd10: RDD[String] = sc.makeRDD(List("hello", "taylor", "taylor"))
    val zeroValue = Map[String, Int]()
    // fold算子必须保证分区内和分区间的数据类型保持一致
    val value: RDD[Map[String, Int]] = rdd10.map(
      word => {
        Map[String, Int](word -> 1) //转化为map
      }
    )
    val wordCountMap: Map[String, Int] = value.fold(zeroValue)(
      (map1, map2) => {
        map1.foldLeft(map2)(
          (m1, m2) => {
            val k: String = m2._1
            val v: Int = m2._2
            val i: Int = m1.getOrElse(k, 0)
            m1.updated(k, i + v)

          }
        )
      }
    )

    println(wordCountMap.mkString(" "))

    //方法11 reduce
    val rdd11: RDD[String] = sc.makeRDD(List("hello", "taylor", "taylor"))
    val value11: RDD[Map[String, Int]] = rdd11.map(
      word => {
        Map[String, Int](word -> 1) //转化为map
      }
    )
    val wordCountMap11: Map[String, Int] = value11.reduce(
      (map1, map2) => {
        map1.foldLeft(map2)(
          (m1, m2) => {
            val k: String = m2._1
            val v: Int = m2._2
            val i: Int = m1.getOrElse(k, 0)
            m1.updated(k, i + v)

          }
        )
      }
    )
    println(wordCountMap11.mkString(" "))


  }


}
