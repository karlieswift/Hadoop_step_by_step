package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 14:53 
 *         ClassName: SparkSql_Save  
 * @version java "13.0.1"
 *        本地内置hive
 */
object SparkSql_Hive {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()


    spark.sql("show tables").show()
//    spark.sql("create table student(id int)")
//    spark.sql("load data local inpath 'file/ids.txt' into table student")
    spark.sql("select *from student").show()




   spark.stop()

  }
}
