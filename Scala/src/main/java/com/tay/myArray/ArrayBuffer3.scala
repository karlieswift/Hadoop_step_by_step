package com.tay.myArray

import scala.collection.mutable.ArrayBuffer

/**
 * @author karlieswift 
 *         date: 2020/7/1 10:51 
 *         ClassName: ArrayBuffer3  
 * @version java "13.0.1"
 */
object ArrayBuffer3 {

  def main(args: Array[String]): Unit = {
    val buffer1 = ArrayBuffer(1,2,3)
    val buffer2 = ArrayBuffer(5,6,7)

    val buffer3 = buffer1+=4
    println(buffer3.mkString(" ")) //1 2 3 4
    println(buffer1.mkString(" "))  //1 2 3 4
    println(buffer1 eq buffer3)  //true

    /**
     * ++ 会产生新的数组
     * ++= 会更新前面的数组
     */
    val buf1 = buffer1++buffer2
    val buf2 = buffer1++=buffer2
    println(buf1) //ArrayBuffer(1, 2, 3, 4, 5, 6, 7)
    println(buf2) //ArrayBuffer(1, 2, 3, 4, 5, 6, 7)
    println(buffer1) //ArrayBuffer(1, 2, 3, 4, 5, 6, 7)
    println(buf2 eq buf1) //false
    println(buf2 eq buffer1)  //true

  }
}
