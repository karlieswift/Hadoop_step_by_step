package com.tay.myQueueAndStack
import scala.collection.mutable.Stack
/**
 * @author karlieswift 
 *         date: 2020/7/2 15:43 
 *         ClassName: Stack1  
 * @version java "13.0.1"
 */
object Stack1 {


  def main(args: Array[String]): Unit = {
    val stack = Stack[Int]()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    println(stack.mkString(" "))
    println(stack.pop())
  }
}
