package com.tay.myException

/**
 * @author karlieswift 
 *         date: 2020/7/5 14:08 
 *         ClassName: Exception1  
 * @version java "13.0.1"
 *
 *          Scala中的异常不区分所谓的编译时异常和运行时异常，
 *          也无需显示抛出方法异常，所以Scala中没有throws关键字。
 */
object Exception1 {

  def main(args: Array[String]): Unit = {
    try{
      var i=1/0
    }catch {
      case x: ArithmeticException => {
        println("算数异常")
      }
      case y: Exception => {
        println("其他异常")
      }
    }
//    finally {
//      println("finally")
//    }

  }
}
