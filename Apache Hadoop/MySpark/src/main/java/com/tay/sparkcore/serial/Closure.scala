package com.tay.sparkcore.serial

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/14 21:54 
 *         ClassName: Closure
 * @version java "13.0.1"
 *
 *          Closure闭包
 *
 *
 *          1)	闭包检查
 *          从计算的角度, 算子以外的代码都是在Driver端执行, 算子里面的代码都是在Executor端执行。那么在scala的函数式编程中，
 *          就会导致算子内经常会用到算子外的数据，这样就形成了闭包的效果，如果使用的算子外的数据无法序列化，
 *          就意味着无法传值给Executor端执行,就会发生错误，所以需要在执行任务计算前，
 *          检测闭包内的对象是否可以进行序列化，这个操作我们称之为闭包检测。Scala2.12版本后闭包编译方式发生了改变
 */
object Closure {

  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)
    val list=List(1,2,3,4,5,6)
    val rdd: RDD[Int] = sc.makeRDD(list)


    // 下面的代码是在Driver端执行
    var i=1
    rdd.foreach(
    ls=>{
        // 下面的代码是在Executor端执行的
        // 那么需要将变量i包含到当前匿名函数的内部，改变变量的生命周期
        // 所以存在闭包。
        // 这里之所以会使用闭包，Driver会将变量i传递给Executor
        // 这个变量i就是通过闭包 检测出来的。
        // 所以Spark框架在执行作业前，必须进行闭包检测功能。
        println(ls+i)
      }
    )



    sc.stop()
  }

}
