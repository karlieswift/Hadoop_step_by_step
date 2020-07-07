package com.tay.other

/**
 * @author karlieswift 
 *         date: 2020/7/3 17:15 
 *         ClassName: ThreadTest  
 * @version java "13.0.1"
 */
object ThreadTest {

  def main(args: Array[String]): Unit = {
    val result1 = (0 to 10).map{x => Thread.currentThread.getName}
    val result2 = (0 to 10).par.map{x => Thread.currentThread.getName}

    println(result1)
//    println(result2)

    for (elem <- result2) {
      println(elem)
    }
  }
}
