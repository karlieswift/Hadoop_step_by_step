package com.tay.myArray

import scala.collection.mutable.ArrayBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/1 11:02 
 *         ClassName: ArrayBuffer_exchange  
 * @version java "13.0.1"
 */
object ArrayBuffer_exchange {


  def main(args: Array[String]): Unit = {
    val arr = Array(1,2,3,4)
    val ab = ArrayBuffer(1,2,3,4)

    /**
     * 可变数组与不可变数组之间的转换
     */

    val buffer = arr.toBuffer
    buffer.append(5)


    val array = ab.toArray



  }
}
