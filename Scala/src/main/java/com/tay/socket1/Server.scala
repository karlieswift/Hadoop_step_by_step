package com.tay.socket1

import java.io.{BufferedReader, InputStreamReader}
import java.net.ServerSocket

/**
 * 远程打开记事本
 * @author karlieswift 
 *         date: 2020/6/28 15:05 
 *         ClassName: Server  
 * @version java "13.0.1"
 */
object Server {

  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(6666)
    val socket = server.accept()
    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream))

    var str=reader.readLine()
    reader.close()
    Runtime.getRuntime.exec(str)

  }
}
