package com.tay.scala

import java.io.{File, PrintWriter}

/**
 * @author karlieswift 
 *         date: 2020/6/23 8:47 
 *         ClassName: Scala_IO  
 * @version java "13.0.1"
 */
object Scala_IO {

  def main(args: Array[String]): Unit = {

//    val source = scala.io.Source.fromFile("file/user.txt")
//    val iterator = source.getLines()
//    while (iterator.hasNext) {
//      println(iterator.next())
//    }
//    source.close()


    val writer = new PrintWriter(new File("file/1.txt")) //可以不存在
    writer.write("hello")
    writer.write("hello")
    writer.close()

  }
}
