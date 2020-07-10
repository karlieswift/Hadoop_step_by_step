package com.tay.sparkFramwork.core
import com.tay.sparkFramwork._
import com.tay.sparkFramwork.util.EnvUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/10 9:47 
 *         ClassName: TServese  
 * @version java "13.0.1"
 */
trait Tapplication {


  def excute(op: =>Unit): Unit ={
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("tay")
    val sc = new SparkContext(sparkConf)
    EnvUtils.setEnv(sc)
    try {
      op
    } catch {
      case exception: Exception =>
        exception.printStackTrace()
    }
    sc.stop()


  }

}
