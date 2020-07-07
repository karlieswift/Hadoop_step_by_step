package com.tay.myList

import scala.collection.mutable.ListBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/1 11:40 
 *         ClassName: ListBuffer3Exchange
 * @version java "13.0.1"
 */
object ListBuffer3Exchange {

  def main(args: Array[String]): Unit = {

    val list = List(1,2)
    val listBuffer = ListBuffer(1,2)

    println(list==listBuffer) //true
//    println(list.toBuffer)
//    println(listBuffer.toList)


//    println(listBuffer ++ list)
//    println(listBuffer .:+( list.toBuffer))
//    println(listBuffer ++ list.toBuffer)


    val buffer1 = listBuffer .:+( list.toBuffer)
    var buffer2=listBuffer ++ list.toBuffer
    println(buffer1.length)
    println(buffer2.length)
//    println(buffer1 == buffer2)

  }

}
