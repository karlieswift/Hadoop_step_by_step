package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql._

/**
 * @author karlieswift 
 *         date: 2020/7/20 10:31 
 *         ClassName: SparkSql_UDAF2
 * @version java "13.0.1"
 *          用户自定义函数
 *          用户可以通过spark.udf功能添加自定义函数，实现自定义功能。
 *
 *          Spark3.0版本可以采用强类型的Aggregate方式代替UserDefinedAggregateFunction
 *          自定义求平均值
 */
object SparkSql_UDAF2 {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")

    //创建SparkSession环境
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    val df: DataFrame = spark.read.json("file/user.json")



    //TODO 自定义函数

    //todo sql是不支持强类型集合函数
    val ds: Dataset[User] = df.as[User]
    //创建集合函数
    val uDAF = new MyAvgAgeUDAF
    //todo 因为集合函数是强类型的，那么sql没有类型的概念，所有无法在sql中执行
    //todo 因此采用DSL语法进行访问
    ds.select(uDAF.toColumn).show()
    spark.stop()
  }

  case class User(name:String,age:Long)

  case class Buff(var sum: Long, var count: Long)

  /**
   * 继承import org.apache.spark.sql.expressions.Aggregator
   * INput:User 输入的类型User
   * Buffer:Buff ：缓存区的数据类型
   * OUT:Double  输出的结果Double
   *
   */
  class MyAvgAgeUDAF extends Aggregator[User, Buff, Double] {
    override def zero: Buff = Buff(0,0)

    override def reduce(b: Buff, a: User): Buff = {

      b.sum+=a.age
      b.count+=1
      b
    }

    override def merge(b1: Buff, b2: Buff): Buff = {
      b1.sum+=b2.sum
      b1.count+=b2.count
      b1
    }

    override def finish(reduction: Buff): Double = {
      reduction.sum/reduction.count
    }

    override def bufferEncoder: Encoder[Buff] = Encoders.product

    override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
  }

}
