package com.tay.socket2

import java.io.{BufferedReader, BufferedWriter, InputStream, InputStreamReader, ObjectInputStream, ObjectOutputStream, PrintWriter}
import java.net.{ServerSocket, Socket}

import jdk.internal.org.objectweb.asm.util.Printer

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

    val reader = new ObjectInputStream(socket.getInputStream)


    var task=reader.readObject().asInstanceOf[Task]
    reader.close()
    val compute = task.compute

    //得到的计算结果返回

    val socket1 = new Socket("localhost",9999)
    //方法一
    val writer = socket1.getOutputStream.write(compute)
    //方法二
    //val writer = new PrintWriter(socket1.getOutputStream)
     //writer.write(compute)

    socket1.close()

  }

}
