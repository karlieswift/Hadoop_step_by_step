package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/13 15:35 
 *         ClassName: Rdd_sortByKey  
 * @version java "13.0.1"
 *          自定义排序  类要实现sortByKey
 */
object Rdd_sortByKey {
  def main(args: Array[String]): Unit = {


    val list: List[(String, Int)] = List(("a", 88), ("b", 95), ("c", 91), ("e", 93), ("d", 95))

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(list,2)

    //存在shuffle
    rdd.sortByKey(false).collect().foreach(println)

    /**
     * (e,93)
     * (d,95)
     * (c,91)
     * (b,95)
     * (a,88)
     */


    /**
     * 如果类要实现sortByKey，需要实现特质Ordered接口
     *  //在一个(K,V)的RDD上调用，K必须实现Ordered接口，返回一个按照key进行排序的
     */

    val tuples = List(
      (new User(12), 1),(new User(93), 1), (new User(33), 1)
    )
   val rdd1: RDD[(User, Int)] = sc.makeRDD(tuples,2)
    rdd1.sortByKey().collect().foreach(println)

    /**
     * (User(93),1)
     * (User(33),1)
     * (User(12),1)
     */
    sc.stop()


  }

}
case class User(age:Int) extends Ordered[User]{
  override def compare(that: User): Int = {
    that.age-age //降序
  }
}
