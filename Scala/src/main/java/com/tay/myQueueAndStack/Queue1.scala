package com.tay.myQueueAndStack
import scala.collection.mutable.Queue
/**
 * @author karlieswift 
 *         date: 2020/7/1 14:52 
 *         ClassName: Queue1  
 * @version java "13.0.1"
 */
object Queue1 {


  def main(args: Array[String]): Unit = {

    val q = Queue(1,2,3,4)
    for (elem <- q) {
      println(elem)
    }
    println(q)
    val i = q.dequeue() //出队列
    println(i)
    println(q)

    q.enqueue('a') //进队
    println(q) //Queue(2, 3, 4, 97)

  }
}
