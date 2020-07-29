package com.tay.sparkSql

import org.apache.spark.{SparkConf, sql}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 9:05 
 *         ClassName: SparkSql_DSL
 * @version java "13.0.1"
 *
 *
 *          DSL
 */
object SparkSql_DSL {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")

    //创建SparkSession环境
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //读取文件
    val df: DataFrame = spark.read.json("file/user.json")
    //    df.show()

    /**
     * DSL
     * DataFrame提供一个特定领域语言(domain-specific language, DSL)去管理结构化的数据。可以在 Scala, Java,
     * Python 和 R 中使用 DSL，使用 DSL 语法风格不必去创建临时视图了
     */

    df.select("name", "age").show()
    df.select("*").show()
    //导入环境对象的变量引用名称 ，spark.implicits的spark是 上面自己创建的val spark
    import spark.implicits._

    df.select($"age" + 1).show()
    df.select('age + 1, 'name).show()


    spark.stop()

  }
}
