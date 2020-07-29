package com.tay.sparkSql.user_visit

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author karlieswift 
 *         date: 2020/7/20 14:53 
 *         ClassName: Product_Datas
 * @version java "13.0.1"
 *         生产数据表
 *         user_visit_action
 *         product_info
 *         city_info
 *
 */
object Product_Datas {
  def main(args: Array[String]): Unit = {
    /**
     * 这里会出现访问权限问题，需要指定用户的权限名，加入以下代码：
     *                System.setProperty("HADOOP_USER_NAME", "tay")  这里的tay就是hadoop集群的用户名
     *
     * WARNING: All illegal access operations will be denied in a future release
     * 20/07/21 09:17:30 ERROR log: Got exception: org.apache.hadoop.security.AccessControlException Permission denied:
     * user=karlieswift, access=WRITE, inode="/user/hive/warehouse/tay.db":tay:supergroup:drwxr-xr-x
     *
     */

    //todo 设置属性
    System.setProperty("HADOOP_USER_NAME", "tay")

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    //todo 连接Hive
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()

    spark.sql("use tay")

    spark.sql(
      """
        |CREATE TABLE `user_visit_action`(
        |  `date` string,
        |  `user_id` bigint,
        |  `session_id` string,
        |  `page_id` bigint,
        |  `action_time` string,
        |  `search_keyword` string,
        |  `click_category_id` bigint,
        |  `click_product_id` bigint,
        |  `order_category_ids` string,
        |  `order_product_ids` string,
        |  `pay_category_ids` string,
        |  `pay_product_ids` string,
        |  `city_id` bigint)
        |row format delimited fields terminated by '\t'
        |""".stripMargin
    )
    spark.sql(
      """
        |load data local inpath 'file/user_visit_action1.txt' into table user_visit_action;
        |""".stripMargin
    )
    spark.sql(
      """
        |CREATE TABLE `product_info`(
        |  `product_id` bigint,
        |  `product_name` string,
        |  `extend_info` string)
        |row format delimited fields terminated by '\t'
        |
        |""".stripMargin)
    spark.sql(
      """
        |load data local inpath 'file/product_info.txt' into table product_info
        |""".stripMargin)
    spark.sql(
      """
        |CREATE TABLE `city_info`(
        |  `city_id` bigint,
        |  `city_name` string,
        |  `area` string)
        |row format delimited fields terminated by '\t'
        |
        |""".stripMargin)
    spark.sql(
      """
        |load data local inpath 'file/city_info.txt' into table city_info
        |""".stripMargin)
   spark.stop()

  }
}
