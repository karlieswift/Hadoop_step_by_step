package com.tay.sparkcore.wordcountFramework

import com.tay.sparkFramwork.core.Tapplication
import com.tay.sparkFramwork.util.EnvUtils
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/7 15:36
 *         ClassName: WordCount1
 * @version java "13.0.1"
 *
 *          封装三层架构
 *
 */
object WordCount extends App with Tapplication{


  excute{
    val contoller = new WordcountContoller
    contoller.dispatcher()
  }

}
