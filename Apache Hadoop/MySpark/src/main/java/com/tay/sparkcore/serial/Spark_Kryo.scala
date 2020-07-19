package com.tay.sparkcore.serial
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
/**
 * @author karlieswift 
 *         date: 2020/7/15 7:56 
 *         ClassName: Spark_Kryo  
 * @version java "13.0.1"
 *
 *          3)	Kryo序列化框架
 *          参考地址: https://github.com/EsotericSoftware/kryo
 *          Java的序列化能够序列化任何的类。但是比较重（字节多），序列化后，对象的提交也比较大。Spark出于性能的考虑，
 *          Spark2.0开始支持另外一种Kryo序列化机制。Kryo速度是Serializable的10倍。当RDD在Shuffle数据的时候，
 *          简单数据类型、数组和字符串类型已经在Spark内部使用Kryo来序列化。
 *          注意：即使使用Kryo序列化，也要继承Serializable接口。
 */
object Spark_Kryo {
  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setAppName("SerDemo").setMaster("local[*]")
      // 替换默认的序列化机制
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      // 注册需要使用 kryo 序列化的自定义类
      .registerKryoClasses(Array(classOf[Searcher1]))

    val sc = new SparkContext(conf)

    val rdd: RDD[String] = sc.makeRDD(Array("hello world", "hello taylor", "taylor", "hahah"), 2)

    val Searcher1 = new Searcher1("hello")
    val result: RDD[String] = Searcher1.getMatchedRDD1(rdd)

    result.collect.foreach(println)
    sc.stop()
  }
}
case class Searcher1(val query: String) {

  def isMatch(s: String) = {
    s.contains(query)
  }

  def getMatchedRDD1(rdd: RDD[String]) = {
    rdd.filter(isMatch)
  }

  def getMatchedRDD2(rdd: RDD[String]) = {
    val q = query
    rdd.filter(_.contains(q))
  }

}
