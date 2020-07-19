package com.tay.sparkcore.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/14 15:16 
 *         ClassName: Action_Fun
 * @version java "13.0.1"
 *
 *          行动算子:其实就是通过执行对应的方法，来运行作业
 */
object Action_Fun {


  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,7,11,4,5,6)
    val rdd: RDD[Int] = sc.makeRDD(list)


    //1-行动算子调用一次，作业就会执行一次
    val i: Int = rdd.reduce(_+_)
    println(i)



    //2-返回RDD中元素的个数
    val l: Long = rdd.count()
    println(l)
    //3-返回RDD中的第一个元素
    println(rdd.first())
//    4-返回一个由RDD的前n个元素组成的数组
    val ints: Array[Int] = rdd.take(2)
    println(ints.mkString(" "))

    //5-返回该RDD排序后的前n个元素组成的数组
    val ints1: Array[Int] = rdd.takeOrdered(3)
    println( ints1.mkString(" "))

    //6-aggregate 分区的数据通过初始值和分区内的数据进行聚合，然后再和初始值进行分区间的数据聚合
    val list1=List(1,2,6)
    val rdd1: RDD[Int] = sc.makeRDD(list1,2)
    //分区的数据通过初始值和分区内的数据进行聚合，然后再和初始值进行分区间的数据聚合
    val i1: Int = rdd1.aggregate(10)(_+_,_+_)
    val rdd2: RDD[Int] = sc.makeRDD(list1,3)
    val i2: Int = rdd2.aggregate(10)(_+_,_+_)
    println(i1+":"+i2)//39:49
    //7-fold 就是aggregate的简化
    val i3: Int = rdd1.fold(10)(_+_)
    println(i3)

    //8-countByKey
    val list3=List( ("a",1),("a",2),("c",3),("e",66))
    val rdd31: RDD[(String, Int)] = sc.makeRDD(list3)
    val stringToLong: collection.Map[String, Long] = rdd31.countByKey()
    println(stringToLong)//Map(a -> 2, c -> 1, e -> 1)

    //9-countByValue
    val list4=List("hello","taylor","hello")
    val rdd4: RDD[String] = sc.makeRDD(list4)
    val tupleToLong: collection.Map[(String, Int), Long] = rdd4.map((_,1)).countByValue()
    println(tupleToLong)//Map((taylor,1) -> 1, (hello,1) -> 2)


    sc.stop()


  }
}
