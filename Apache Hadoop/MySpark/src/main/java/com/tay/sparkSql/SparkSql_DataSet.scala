package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 9:05 
 *         ClassName: SparkSql_DSL
 * @version java "13.0.1"
 *
 *  Dataset--->DataFrame
 *  DataFrame--->Dataset
 *  其实DataFrame就是特定类型的DataSet
 *  源码：type DataFrame = Dataset[Row]
 *
 *
 */
object SparkSql_DataSet {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")

    //创建SparkSession环境
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //读取文件
    val df: DataFrame = spark.read.json("file/user.json")
    //导入环境对象的变量引用名称 ，spark.implicits的spark是 上面自己创建的val spark
    import spark.implicits._
    //DataFrame--->Dataset
    //转换时，需要属性类型和属性名字，顺序都要一样
    val ds: Dataset[User] = df.as[User]
    ds.show()


    // Dataset--->DataFrame
//    其实DataFrame就是特定类型的DataSet   type DataFrame = Dataset[Row]
    val frame: DataFrame = ds.toDF()
    frame.show()
    spark.stop()

  }
  case class User( name:String,age:Long)
}
