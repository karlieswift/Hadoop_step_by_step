package com.tay.sparkFramwork.util

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author karlieswift 
 *         date: 2020/7/10 9:42 
 *         ClassName: EnvUtils  
 * @version java "13.0.1"
 */
object EnvUtils {


  private val threadLocal = new ThreadLocal[SparkContext]

  def setEnv(sc: SparkContext ): Unit ={
    threadLocal.set(sc)
  }

   def getEnv() ={
     threadLocal.get()
  }
}
