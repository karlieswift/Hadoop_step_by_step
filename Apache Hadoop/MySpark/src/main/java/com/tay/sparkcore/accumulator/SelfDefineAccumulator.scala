package com.tay.sparkcore.accumulator

import scala.collection.mutable
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}

/**
 * @author karlieswift 
 *         date: 2020/7/16 12:08 
 *         ClassName: SelfDefineAccumulator  
 * @version java "13.0.1"
 *          累加器
 *          实现原理:
 *          累加器用来把Executor端变量信息聚合到Driver端。在Driver程序中定义的变量，
 *          在Executor端的每个Task都会得到这个变量的一份新的副本，每个task更新这些副本的值后，传回Driver端进行merge。
 *
 *
 *          2-自定义累加器
 *          实现 List("haha","hadoop","hive","haha","hadoop","haha")单词统计
 *
 *
 *
 */
object SelfDefineAccumulator {

  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)

    val list = List("haha", "hadoop", "hive", "haha", "hadoop", "haha")
    val rdd: RDD[String] = sc.makeRDD(list)
    //声明累加器
    val wordcountAccumulator = new WordcountAccumulator
    //注册
    sc.register(wordcountAccumulator, "wordcount")

    rdd.foreach(
      number => {
        //使用累加器
        wordcountAccumulator.add(number)
      }
    )
    println(wordcountAccumulator.value) // Map(hadoop -> 2, hive -> 1, haha -> 3)
    sc.stop()
  }

}

/**
 * 自定义累加器
 *  1. 继承AccumulatorV2，并设定泛型
 *              这里设置的泛型为:AccumulatorV2[String,mutable.Map[String,Long]]
 *              1- String :代表输入的数据为String数据
 * (例如这里的数据List("haha","hadoop","hive","haha","hadoop","haha"))
 *              如果数据是 ：List(("ha",1),("dd",2)) 泛型可以为:AccumulatorV2[(String,Long),mutable.Map[String,Long]]
 *              2- mutable.Map[String,Long]：代表输出的数据格式
 *  2. 重写累加器的抽象方法----6个方法
 *  3.  注册
 *     sc.register(wordcountAccumulator,"wordcount")
 *
 * override def isZero: Boolean = ???// 累加器是否为初始状态
 * override def copy(): AccumulatorV2[Nothing, Nothing] = ???// 复制累加器
 * override def reset(): Unit = ???// 重置累加器
 * override def add(v: Nothing): Unit = ???// 向累加器中增加数据
 * override def merge(other: AccumulatorV2[Nothing, Nothing]): Unit = ???// 合并累加器
 * override def value: Nothing = ???// 返回累加器的结果
 */
class WordcountAccumulator extends AccumulatorV2[String, mutable.Map[String, Long]] {

  private var map: mutable.Map[String, Long] = mutable.Map()

  //累加器是否为初始状态
  override def isZero: Boolean = {
    map.isEmpty
  }

  //复制累加器
  override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = {
    new WordcountAccumulator
  }

  //重置累加器
  override def reset(): Unit = {
    map.clear()
  }

  //添加数据
  override def add(v: String): Unit = {
    // 查询map中是否存在相同的单词
    // 如果有相同的单词，那么单词的数量加1
    // 如果没有相同的单词，那么在map中增加这个单词
    val t: Long = map.getOrElse(v, 1L)
    map.update(v, t)
  }

  //合并累加器
  override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {

    val othermap: mutable.Map[String, Long] = other.value
    val tempmap = map

    map = tempmap.foldLeft(othermap)(
      (map1, map2) => {
        //合并累加器
        val k2: String = map2._1
        val v2: Long = map2._2
        val temp = map1.getOrElse(k2, 0L)
        map1.update(k2, temp + v2)
        map1
      }
    )


  }

  //返回累加器的结果
  override def value: mutable.Map[String, Long] = {
    map
  }
}

