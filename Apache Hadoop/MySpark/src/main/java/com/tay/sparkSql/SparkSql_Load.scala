package com.tay.sparkSql

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 14:52 
 *         ClassName: SparkSql_Load  
 * @version java "13.0.1"
 *          通用的加载和保存方式
 *          SparkSQL提供了通用的保存数据和数据加载的方式。这里的通用指的是使用相同的API，
 *          根据不同的参数读取和保存不同格式的数据，SparkSQL默认读取和保存的文件格式为parquet
 *
 *          json
 *          csv
 *          sql
 */
object SparkSql_Load {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")

    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //todo 加载json文件
    spark.read.json("file/user.json")
    //todo 加载parquet文件
    spark.read.load("file/users.parquet")

    //todo 加载csv
    spark.read.format("csv")
      .option("sep", ";")
      .option("inferSchema", "true")
      .option("header", "true")
      .load("file/people.csv").show()



    println("------------------------------------------")

    /**
     * 加载数据库文件
     */
    //方式1：通用的load方法读取
    spark.read.format("jdbc")
      .option("url", "jdbc:mysql://hadoop7:3306/test")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "person")
      .load().show

//
    //方式2:通用的load方法读取 参数另一种形式
    spark.read.format("jdbc")
      .options(Map("url"->"jdbc:mysql://hadoop7:3306/test?user=root&password=123456",
        "dbtable"->"person","driver"->"com.mysql.jdbc.Driver")).load().show

    //方式3:使用jdbc方法读取
    val props: Properties = new Properties()
    props.setProperty("user", "root")
    props.setProperty("password", "123456")
    val df: DataFrame = spark.read.jdbc("jdbc:mysql://hadoop7:3306/test", "person", props)
    df.show



spark.stop()

  }
}
