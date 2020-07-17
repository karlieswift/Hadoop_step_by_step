package com.tay.sparkcore.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/8 18:01 
 *         ClassName: RDD_Parall2  
 * @version java "13.0.1"
 *          内存文件分区测试分析：
 *
 *
 *          在加载数据时，可以设定并行度来设置分区数量
 *          Spark中读取文件数据，其实采用的是Hadoop文件读取方式。
 *          1. 分区数量到底是多少？
 *
 *          Spark是不能决定具体的分区数量，只能提供最小分区数量
 *          具体的分区数量是由hadoop在读取文件时自动判断的。
 *          文件总字节大小： totalsize = 19B
 *          每个分区应该读取的字节大小： long goalSize = totalSize / (numSplits == 0 ? 1 : numSplits);
 *          使用总的字节数除以分区数量，看余数和每个分区字节数的比率是否超过10%，如果超过，需要一个新的分区
 *          最终分区数量 = 最小分区数 + 可能的分区数量（0，1）
 *
 *         2. 每个分区存储什么数据？
 *
 *          Spark不决定数据如何存储，依然是由Hadoop来决定
 *         2.1 Hadoop读取文件是一行一行读取的，不是按照字节的方式.
 *         2.2 Hadoop读取数据是按照数据的偏移量的读取的，偏移量从0开始的。
 *
 *          文件读取 分区的规则
 *
 *   sc.textFile("file/1.txt",3) 设置三个分区，但实际是三个还是4个，需要下面计算
 *   分析：
 *          例如文件file/1.txt里存在 四行数据总共19B(注意1-3行结尾会存在回车2个字节)
 *          12
 *          345
 *          678
 *          91011
 *
 *          19B / 3个分区 = 6B...1B  (1B/6B=16.7%>10%，所以需要再加一个分区，因此是四个分区)
 *          分区读取数据的规则：
 *          数据：
 *          12xx  0,1,2,3
 *          345xx  4,5,6,7,8,9
 *          678xx  10,11,12,13,14
 *          91011xx 15,16,17,18,19
 *
 *          part-00000：0 -- 6    【12,345】
 *          part-00001：6 -- 12 => 3 - 6 => 【678】
 *          part-00002 :12 -- 18 => 6 - 7 => 【91011】
 *          part-00003 : 18 -- 19 => 6 - 7 => 【】为空
 *
 */
object RDD_Parall2 {

  def main(args: Array[String]): Unit = {
    val rddConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(rddConf)

    /**
     * hadoopFile(path, classOf[TextInputFormat]的TextInputFormat==> FileInputFormat
     *  FileInputFormat----
     *              public InputSplit[] getSplits(JobConf job, int numSplits)
     *                      每个分区应该读取的字节大小
     *                     long goalSize = totalSize / (numSplits == 0 ? 1 : numSplits);
     *
     */
    val files: RDD[String] = sc.textFile("file/1.txt",3)
    files.saveAsTextFile("output")
    sc.stop()



  }
}
