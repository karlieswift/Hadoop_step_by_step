package com.tay.socket2

import java.io.{BufferedInputStream, BufferedReader, InputStreamReader, ObjectOutputStream, PrintWriter}
import java.net.{ServerSocket, Socket}

/**
 * @author karlieswift 
 *         date: 2020/6/23 12:14 
 *         ClassName: Client  
 * @version java "13.0.1"
 */
object Client {
  def main(args: Array[String]): Unit = {

    val socket = new Socket("localhost",6666)
    val writer = new ObjectOutputStream(socket.getOutputStream)

    writer.writeObject(new Task)
    writer.flush()
    writer.close()

    //接收计算结果

    val server1 = new ServerSocket(9999)
    val socket1 = server1.accept()

    val reader = new BufferedReader(new InputStreamReader(socket1.getInputStream))

    val i = reader.read()

    reader.close()
    print(i)
  }
}
