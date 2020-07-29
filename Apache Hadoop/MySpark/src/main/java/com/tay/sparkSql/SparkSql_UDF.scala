package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 10:31 
 *         ClassName: SparkSql_UDAF  
 * @version java "13.0.1"
 *          用户自定义函数
 *          用户可以通过spark.udf功能添加自定义函数，实现自定义功能。
 */
object SparkSql_UDF {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")

    //创建SparkSession环境
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
    //读取文件
    val df: DataFrame = spark.read.json("file/user.json")

    //TODO 自定义函数
    //添加字段
    spark.udf.register("addmes",(name:String)=>{
      "Name:"+name
    })

    df.createOrReplaceTempView("user")
    spark.sql("select addmes(name) from user").show()
    spark.sql("select avg(age) from user").show()

    /**
     * +------------+
     * |addmes(name)|
     * +------------+
     * | Name:taylor|
     * | Name:karlie|
     * |  Name:swift|
     * |  Name:kloss|
     * +------------+
     */





    spark.stop()
  }



}
