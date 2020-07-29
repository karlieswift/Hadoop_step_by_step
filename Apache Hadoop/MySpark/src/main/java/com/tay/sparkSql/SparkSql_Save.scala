package com.tay.sparkSql

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 14:53 
 *         ClassName: SparkSql_Save  
 * @version java "13.0.1"
 *          通用的加载和保存方式
 *          SparkSQL提供了通用的保存数据和数据加载的方式。这里的通用指的是使用相同的API
 *          ，根据不同的参数读取和保存不同格式的数据，SparkSQL默认读取和保存的文件格式为parquet
 */
object SparkSql_Save {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    val df: DataFrame = spark.read.json("file/user.json")


    //todo 将数据保存到指定位置  默认为格式为parquet
//    df.write.save("output")
//    //todo 指定格式
//    df.write.format("json").save("output1")

    // todo 如果要写入同一个路径

    /**
     * 如果要写入同一个路径
     * SaveMode是一个枚举类，其中的常量包括：
     * Scala/Java	Any Language	Meaning
     * SaveMode.ErrorIfExists(default)	"error"(default)	如果文件已经存在则抛出异常
     * SaveMode.Append	"append"	如果文件已经存在则追加
     * SaveMode.Overwrite	"overwrite"	如果文件已经存在则覆盖
     * SaveMode.Ignore	"ignore"	如果文件已经存在则忽略
     */
    // todo 追加
//    df.write.format("json").mode("append").save("output1")



    /**
     * 数据写入数据库
     */

      import spark.implicits._
    val tuples = List((2,"karlir",22),(3,"karlir",22))
      val rdd: RDD[(Int,String, Int)] = spark.sparkContext.makeRDD(tuples )
      val ds: DataFrame = rdd.toDF("id","name","age")
    //方式1：通用的方式  format指定写出类型
    ds.write
      .format("jdbc")
      .option("url", "jdbc:mysql://hadoop7:3306/test")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "person")
      .mode(SaveMode.Append)
      .save()

    //方式2：通过jdbc方法
    val props: Properties = new Properties()
    props.setProperty("user", "root")
    props.setProperty("password", "123456")
    ds.write.mode(SaveMode.Append).jdbc("jdbc:mysql://hadoop7:3306/test", "person", props)



    spark.stop()
  }
}
