package com.tay.sparkSql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * @author karlieswift 
 *         date: 2020/7/20 9:05 
 *         ClassName: SparkSql_DSL
 * @version java "13.0.1"
 *
 *
 *         DataFrame--->RDD
 *         RDD->DataFrame
 */
object SparkSql_RDD {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL01")

    //创建SparkSession环境
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //读取文件
    val df: DataFrame = spark.read.json("file/user.json")




    val value: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(
      List(
        (1, "taylor", 23),
        (2, "karlie", 19)
      )
    )

    //导入环境对象的变量引用名称 ，spark.implicits的spark是 上面自己创建的val spark
    //RDD -->DataFrame  需要import spark.implicits._
    import spark.implicits._
    val frame: DataFrame = value.toDF("id","name","age")
    val frame1: DataFrame = value.toDF()

    frame.show()

    /**
     * +---+------+---+
     * | id|  name|age|
     * +---+------+---+
     * |  1|taylor| 23|
     * |  2|karlie| 19|
     * +---+------+---+
     */
    frame1.show()


    //DataFrame--->RDD 不需要import spark.implicits._
    //RDD  返回的RDD类型为Row，里面提供的getXXX方法可以获取字段值，类似jdbc处理结果集，但是索引从0开始
    val rdd: RDD[Row] = frame.rdd
    rdd.foreach(
      row=>{
        println(row.get(0)+" "+row.get(1))
      }
    )

    /**
     * 1 taylor
     * 2 karlie
     */


      //RDD->DataFrame
    val value1: RDD[User] = value.map(
      t => {
        User(t._1, t._2, t._3)
      }
    )
    value1.foreach(println)
    val frame2: DataFrame = value1.toDF()

    frame2.show()


    spark.stop()

  }



  case class User(id:Long,name:String,age:Long)
}
