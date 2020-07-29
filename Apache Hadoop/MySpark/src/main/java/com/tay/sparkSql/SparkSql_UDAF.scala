package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction, UserDefinedFunction}
import org.apache.spark.sql.types.{DataType, IntegerType, LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 10:31 
 *         ClassName: SparkSql_UDAF  
 * @version java "13.0.1"
 *          用户自定义函数
 *          用户可以通过spark.udf功能添加自定义函数，实现自定义功能。
 *
 *          实现方式 - UDAF - 弱类型
 *
 *          自定义求平均值
 */
object SparkSql_UDAF {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")
    //创建SparkSession环境
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._

    val df: DataFrame = spark.read.json("file/user.json")
    df.createOrReplaceTempView("user")


    //TODO 自定义函数
    //添加字段
    spark.udf.register("avgage", new MyAvgAgeUDAF)

    //自定义函数
    spark.sql("select avgage(age) from user").show() //
    //系统函数
    spark.sql("select avg(age) from user").show()


    spark.stop()
  }

  /**
   * 定义类继承UserDefinedAggregateFunction，并重写其中方法
   */
  class MyAvgAgeUDAF extends UserDefinedAggregateFunction {
    //输入的数据结构 聚合函数输入参数的数据类型
    override def inputSchema: StructType = {
      StructType(Array(
        StructField("age", IntegerType)
      ))
    }

    //缓存区数据的结构(totalage,agecount)
    override def bufferSchema: StructType = {
      StructType(Array(
        StructField("totalage", IntegerType),
        StructField("agecount", IntegerType)
      ))
    }

    //数据类型:计算结果的类型
    override def dataType: DataType = IntegerType

    //函数的稳定性
    override def deterministic: Boolean = true

    //缓冲区如何初始化
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer.update(0, 0)
      buffer.update(1, 0)
    }

    //根据输入的数据来更新缓冲区
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      val Sum: Int = buffer.getInt(0) + input.getInt(0)
      val count: Int = buffer.getInt(1) + 1

      buffer.update(0, Sum)
      buffer.update(1, count)
    }

    //对多个缓冲区的合并操作
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      val Sum: Int = buffer1.getInt(0) + buffer2.getInt(0)
      val count: Int = buffer1.getInt(1) + buffer2.getInt(1)

      buffer1.update(0, Sum)
      buffer1.update(1, count)
    }

    //计算结果
    override def evaluate(buffer: Row): Any = {
      buffer.getInt(0) / buffer.getInt(1)
    }
  }

}
