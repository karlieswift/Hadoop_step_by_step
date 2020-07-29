package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{Aggregator}
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, Row, SparkSession, functions}

/**
 * @author karlieswift 
 *         date: 2020/7/20 10:31 
 *         ClassName: SparkSql_UDAF  
 * @version java "13.0.1"
 *          用户自定义函数
 *          用户可以通过spark.udf功能添加自定义函数，实现自定义功能。
 *
 *          Spark3.0版本可以采用强类型的Aggregate方式代替UserDefinedAggregateFunction
 *          自定义求平均值
 */
object SparkSql_UDAF1 {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")

    //创建SparkSession环境
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val df: DataFrame = spark.read.json("file/user.json")
    df.createOrReplaceTempView("user")


    //TODO 自定义函数
    //添加字段
    spark.udf.register("avgage", functions.udaf(new MyAvgAgeUDAF))

    //自定义函数
    spark.sql("select avgage(age) from user").show() //
    //系统函数
    spark.sql("select avg(age) from user").show()


    spark.stop()
  }


  case class Buff(var sum: Long, var count: Long)

  /**
   * 继承import org.apache.spark.sql.expressions.Aggregator
   * INput:Long
   * Buffer:Buff
   * OUT:Double
   *
   */
  class MyAvgAgeUDAF extends Aggregator[Long, Buff, Double] {
    //缓存区初始化
    override def zero: Buff = Buff(0, 0)

    //聚合数据
    override def reduce(b: Buff, a: Long): Buff = {
      b.sum = b.sum + a
      b.count = b.count + 1
      b
    }

    //合并数据
    override def merge(b1: Buff, b2: Buff): Buff = {
      b1.sum = b1.sum + b2.sum
      b1.count = b1.count + b2.count
      b1
    }

    //完成计算
    override def finish(reduction: Buff): Double = {
      reduction.sum / reduction.count
    }

    //SparkSql使用编码器进行对象 序列化
    override def bufferEncoder: Encoder[Buff] = {
      Encoders.product
    }

    override def outputEncoder: Encoder[Double] = {
      Encoders.scalaDouble
    }
  }

}
