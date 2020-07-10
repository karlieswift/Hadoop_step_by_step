package com.tay.sparkcore.wordcountFramework
import com.tay.sparkFramwork.core._
import com.tay.sparkFramwork.util.EnvUtils
import org.apache.spark.rdd.RDD
/**
 * @author karlieswift 
 *         date: 2020/7/10 17:59 
 *         ClassName: WordcountDao  
 * @version java "13.0.1"
 */
class WordcountDao extends TDao {

  override def fromFile(path: String): RDD[String] = {
    EnvUtils.getEnv().textFile(path)
  }


}
