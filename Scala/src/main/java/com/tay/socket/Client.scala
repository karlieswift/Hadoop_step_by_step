package com.tay.socket

import java.io.PrintWriter
import java.net.Socket

/**
 * @author karlieswift 
 *         date: 2020/6/23 12:14 
 *         ClassName: Client  
 * @version java "13.0.1"
 */
object Client {
  def main(args: Array[String]): Unit = {

    val socket = new Socket("localhost",6666)
    val writer = new PrintWriter(socket.getOutputStream)
    writer.write("Hello KarlieKloss")
    writer.flush()
    writer.close()
  }
}
