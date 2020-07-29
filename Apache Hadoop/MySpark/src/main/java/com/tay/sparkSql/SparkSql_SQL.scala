package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 9:05 
 *         ClassName: SparkSql_SQL
 * @version java "13.0.1"
 *
 *          SQL语法
 */
object SparkSql_SQL {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")
    //创建SparkSession环境
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    //读取文件
    val df: DataFrame = spark.read.json("file/user.json")
    //    df.show()

    /**
     * SQL语法
     * SQL语法风格是指我们查询数据的时候使用SQL语句来查询，这种风格的查询必须要有临时视图或者全局视图来辅助
     */
    //将df数据转化为临时的视图createOrReplaceTempView
    df.createOrReplaceTempView("user")
    spark.sql("select *from user").show()

    spark.stop()

  }
}
