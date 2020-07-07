package com.tay.socket1

import java.io.PrintWriter
import java.net.Socket

/**
 * 远程打开记事本
 * @author karlieswift 
 *         date: 2020/6/28 15:05 
 *         ClassName: Client  
 * @version java "13.0.1"
 */
object Client {
  def main(args: Array[String]): Unit = {
    val socket = new Socket("localhost",6666)

    val writer = new PrintWriter(socket.getOutputStream)
    writer.write("CMD /C notepad")
    writer.flush()
    writer.close()
  }
}
