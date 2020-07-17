package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/14 14:16 
 *         ClassName: Rdd_join  
 * @version java "13.0.1"
 *           1- join:在类型为(K,V)和(K,W)的RDD上调用，返回一个相同key对应的所有元素连接在一起的(K,(V,W))的RDD
 *          join:存在笛卡尔积现象；处理的数据量可能会非常大，不推荐使用
 *
 *          2-leftOuterJoin
 *
 *
 *          3-rightOuterJoin
 *
 *
 */
object Rdd_join{
  def main(args: Array[String]): Unit = {


    val list=List( ("a",1),("a",2),("c",3),("e",66))
    val list1=List( ("c",33), ("a",11),("a",22),("d",1))

    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(list)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(list1)

    /**
     * 1-join
     */
    //join:两个rdd,相同的key会value连接在一起
    //join:在类型为(K,V)和(K,W)的RDD上调用，返回一个相同key对应的所有元素连接在一起的(K,(V,W))的RDD
     rdd.join(rdd1).collect().foreach(println)

    /**
     * output:
     * (a,(1,11))
     * (a,(1,22))
     * (a,(2,11))
     * (a,(2,22))
     * (c,(3,33))
     */


    /**
     * 2-左外连接
     *
     */

    rdd.leftOuterJoin(rdd1).collect().foreach(println)
    /**
     * output:
     * (a,(1,Some(11)))
     * (a,(1,Some(22)))
     * (a,(2,Some(11)))
     * (a,(2,Some(22)))
     * (c,(3,Some(33)))
     * (e,(66,None))
     */



    /**
     * 3-右外连接
     *
     */
    rdd.rightOuterJoin(rdd1).collect().foreach(println)

    /**
     * output：
     * (a,(Some(1),11))
     * (a,(Some(1),22))
     * (a,(Some(2),11))
     * (a,(Some(2),22))
     * (c,(Some(3),33))
     * (d,(None,1))
     */

    sc.stop()
  }
}
