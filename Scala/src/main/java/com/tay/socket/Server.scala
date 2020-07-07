package com.tay.socket

import java.io.{BufferedReader, BufferedWriter, InputStream, InputStreamReader}
import java.net.ServerSocket

/**
 * @author karlieswift 
 *         date: 2020/6/23 12:15 
 *         ClassName: Server  
 * @version java "13.0.1"
 */
object Server {
  def main(args: Array[String]): Unit = {

    val net = new ServerSocket(6666)
    val socket = net.accept()

    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream))

    var str=reader.readLine()
    println(str)
    reader.close()

  }

}
