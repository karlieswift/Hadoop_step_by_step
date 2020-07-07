package com.tay.scala_dfs

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

import com.tay.scala_dfs.bean._


import scala.util.Random

/**
 * @author karlieswift 
 *         date: 2020/7/7 8:45 
 *         ClassName: Worker  
 * @version java "13.0.1"
 */
class Worker {

  //简单的生成端口，存在可能端口冲突，报错
  var workerPort = new Random().nextInt(100) + 9000

  def startup( masterHost:String, masterPort:Int ): Unit = {

    // TODO 1. 建立和Master链接，告诉Master，准备好了
    val masterConnection = new Socket(masterHost, masterPort)
    val message = new Message()
    message.workerPort = workerPort
    val objOut = new ObjectOutputStream(masterConnection.getOutputStream)
    objOut.writeObject(message)
    objOut.flush()
    masterConnection.close()

    // TODO 2. 建立Worker服务，等待Master发送任务
    val server = new ServerSocket(workerPort)

    // TODO 3. 当接收到任务时，开始计算
    // 当启动服务器后，会阻塞连接，直到有客户端连接后才会继续执行
    // 所以将这部分的代码使用线程进行封装执行
    new Thread(
      new Runnable {
        override def run(): Unit = {
          val masterTaskConnection = server.accept()
          val objIn = new ObjectInputStream(masterTaskConnection.getInputStream)
          val task = objIn.readObject().asInstanceOf[Task]
          masterTaskConnection.close()
          //println(s"Worker[${workerPort}]接收到任务数据${task.data}")

          // 计算
          val result = task.compute()

          // TODO 4. 建立和Master链接，将计算结果返回
          val masterResultConnection = new Socket(masterHost, masterPort)
          val r = new Result()
          r.index = task.index
          r.port = workerPort
          r.data = result
          val objOut = new ObjectOutputStream(masterResultConnection.getOutputStream)
          objOut.writeObject(r)
          objOut.flush()
          masterResultConnection.close()
        }
      }
    ).start()
  }
}
