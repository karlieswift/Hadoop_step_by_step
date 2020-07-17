package com.tay.sparkFramwork.core

import com.tay.sparkFramwork.util.EnvUtils
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/10 17:50 
 *         ClassName: TDao  
 * @version java "13.0.1"
 */
trait TDao {

  def fromFile(path:String):RDD[String] ={
      EnvUtils.getEnv().textFile(path)
  }
}
