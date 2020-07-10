package com.tay.sparkcore.wordcountFramework
import com.tay.sparkFramwork.core._
import com.tay.sparkFramwork.util.EnvUtils
import org.apache.spark.rdd.RDD
/**
 * @author karlieswift 
 *         date: 2020/7/10 18:00 
 *         ClassName: WordcountService  
 * @version java "13.0.1"
 */
class WordcountService extends TService {
  private val dao = new WordcountDao()

  override def analysis(): Unit = {

  val words: RDD[String]= dao.fromFile("file/wordcount.txt")
    val value: RDD[String] = words.flatMap(_.split(" "))

    val wordmap : RDD[(String, Int)] = value.map(list => {
      (list, 1)
    })

    //spark的reduceByKey比scala的groupBy 和 mapValues效率高
    val result: RDD[(String, Int)] = wordmap.reduceByKey((x,y)=>{
      x+y
    })

    result.collect().foreach(println)
  }
}
