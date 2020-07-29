package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author karlieswift 
 *         date: 2020/7/20 14:53 
 *         ClassName: SparkSql_Save  
 * @version java "13.0.1"
 *         访问linux集群的hive
 *         访问外部的hive需要完成以下步骤
 *         1-添加依赖
 *         2-在创建spark的环境对象,启用hive环境支撑
 *         3-将/opt/module/hive/conf/hive-site.xml文件copy到IDEA当前项目的classpath的resources\hive-site.xml下
 *          如果报错直接将hive-site.xml到 target\classes\hive-site.xml
 *         4-引入mysql驱动，在pom.xml文件加入mysql依赖
 */
object SparkSql_toOuterHive {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    //todo 连接Hive
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()

    //todo 操作hive的数据表
    spark.sql("show databases").show
    //todo 当不指定（use database）数据库时,使用数据库-default
    spark.sql("show tables").show
    spark.sql("select *from ids").show


   spark.stop()

  }
}
