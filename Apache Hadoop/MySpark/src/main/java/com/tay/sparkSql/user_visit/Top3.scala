package com.tay.sparkSql.user_visit

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author karlieswift 
 *         date: 2020/7/21 10:04 
 *         ClassName: Top3  
 * @version java "13.0.1"
 */
object Top3 {

  def main(args: Array[String]): Unit = {
    //todo 设置属性
    System.setProperty("HADOOP_USER_NAME", "tay")

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    //todo 连接Hive
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()

    spark.sql("use tay")
    //todo top3

    spark.sql(
      """
        |select *
        |from (
        |select *,
        |rank() over(partition by t2.area order by t2.clickcount desc) ranks
        |from (
        |select t1.area,t1.product_name, count(*) as clickcount
        |from (
        |select u.*,c.area,c.city_name,p.product_name
        |from user_visit_action u
        |join city_info  c on c.city_id=u.city_id
        |join product_info p on u.click_product_id=p.product_id
        |where u.click_product_id > 0
        |)t1 group by t1.area,t1.product_name
        |)t2
        |)t3 where t3.ranks<4
        |
        |""".stripMargin
    ).show()

    /**
     * +----+------------+----------+-----+
     * |area|product_name|clickcount|ranks|
     * +----+------------+----------+-----+
     * |华东|     商品_86|       371|    1|
     * |华东|     商品_47|       366|    2|
     * |华东|     商品_75|       366|    2|
     * |西北|     商品_15|       116|    1|
     * |西北|      商品_2|       114|    2|
     * |西北|     商品_22|       113|    3|
     * |华南|     商品_23|       224|    1|
     * |华南|     商品_65|       222|    2|
     * |华南|     商品_50|       212|    3|
     * |华北|     商品_42|       264|    1|
     * |华北|     商品_99|       264|    1|
     * |华北|     商品_19|       260|    3|
     * |东北|     商品_41|       169|    1|
     * |东北|     商品_91|       165|    2|
     * |东北|     商品_58|       159|    3|
     * |东北|     商品_93|       159|    3|
     * |华中|     商品_62|       117|    1|
     * |华中|      商品_4|       113|    2|
     * |华中|     商品_57|       111|    3|
     * |华中|     商品_29|       111|    3|
     * +----+------------+----------+-----+
     */
    spark.stop()
  }
}
