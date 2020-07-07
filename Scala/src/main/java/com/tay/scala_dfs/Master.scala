package com.tay.scala_dfs

import scala.collection.mutable
import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

import com.tay.scala_dfs.bean._

/**
 * @author karlieswift 
 *         date: 2020/7/7 8:43 
 *         ClassName: Master  
 * @version java "13.0.1"
 *
 *
 *          最终的结果：
 *
 *          Master已经启动......
 *          Set(9097, 9006, 9018, 9023, 9049, 9013)
 *          Set(9097, 9006, 9018, 9023, 9049, 9013)
 *          Set(9097, 9006, 9018, 9023, 9049, 9013)
 *          Set(9097, 9006, 9018, 9023, 9049, 9013)
 *          Set(9097, 9006, 9018, 9023, 9049, 9013)
 *          Set(9097, 9006, 9018, 9023, 9049, 9013)
 *          给Worker[9097]发送计算任务:List(100, 11)
 *          给Worker[9006]发送计算任务:List(23, 21)
 *          给Worker[9018]发送计算任务:List(2, 6)
 *          接收Worker[9097]计算结果1100
 *          接收Worker[9006]计算结果483
 *          接收Worker[9018]计算结果12
 *          作业最终的执行结果为 ：6375600
 */
object Master extends App {

  val masterPort = 9999
  val masterHost = "localhost"
  val masterServer = new ServerSocket(masterPort)
  val workerNum = 6
  val workerPortSet = mutable.Set[Int]()
  var workerResult : Array[Int] = _
  var mergeLogic : (Int, Int) => Int = _

  /**
   * 启动Master
   */
  def startup(): Unit = {
    println("Master已经启动......")
    // TODO 1. 等待接收数据
    receive()
    // TODO 2. 启动Worker（N），反向注册，告诉master已经准备好了
    for ( i <- 1 to workerNum ) {
      val worker = new Worker()
      worker.startup(masterHost, masterPort)
    }
  }

  /**
   * Master接收数据
   * receive 需要使用线程，如果是同一个线程，等待接收，阻塞线程。导致后续逻辑无法执行
   */
  def receive(): Unit = {
    new Thread(
      new Runnable {
        override def run(): Unit = {
          // TODO 接收数据分几种情况
          //    1. worker已经准备完毕
          //    2. 任务执行完毕
          //    3. 用户提交的数据
          while ( true ) {
            val client = masterServer.accept()
            //println("客户端已经连接")
            // TODO 当获取连接后，应该使用新的线程进行处理
            //      而不是在当前线程中进行处理，会影响效率
            new Thread(
              new Runnable {
                override def run(): Unit = {
                  val objIn = new ObjectInputStream(client.getInputStream)
                  val obj: AnyRef = objIn.readObject()
                  obj match {
                    case msg : Message => {
                      // TODO worker已经准备好了
                      workerPortSet.add(msg.workerPort)
                      println(workerPortSet)
                    }
                    case dat : Data => {

                      // 获取可用的Worker集合
                      val workerIter: Iterator[Int] = workerPortSet.iterator
                      mergeLogic = dat.logic

                      //println("接收到用户提交的作业:" + data)
                      // TODO 分解数据
                      // 1,2,3,4
                      val data: List[Int] = dat.data
                      val wcnt = dat.workerCount
                      workerResult = Array.fill[Int](wcnt)(-1)
                      // 2
                      val perTaskDataCnt = data.size / wcnt
                      //data.slice()// 切分数据
                      val iterator: Iterator[List[Int]] = data.sliding(perTaskDataCnt, perTaskDataCnt)  // 滑动窗口
                      var cnt = 0
                      while (iterator.hasNext) {
                        val ints: List[Int] = iterator.next()
                        // TODO 将不同的数据封装成Task任务
                        val task = new Task()
                        task.index = cnt
                        task.data = ints
                        task.logic = dat.logic
                        // TODO 将不同的任务发送给不同的Worker进行计算
                        val workerPort = workerIter.next()
                        val workerConnection = new Socket("localhost", workerPort)
                        val workerObjOut = new ObjectOutputStream(workerConnection.getOutputStream)
                        workerObjOut.writeObject(task)
                        workerObjOut.flush()
                        workerConnection.close()
                        println(s"给Worker[${workerPort}]发送计算任务:${task.data}")
                        cnt += 1
                      }

                      // TODO 启动监控程序
                      new Thread(
                        new Runnable {
                          override def run(): Unit = {
                            var flg = true
                            while ( flg ) {
                              // 判断
                              flg = workerResult.contains(-1)
                              Thread.sleep(1000)
                            }
                            // TODO Worker已经全部计算完毕, 合并最终的结果
                            val finalResult: Int = workerResult.reduce(mergeLogic)
                            println("作业最终的执行结果为 ：" + finalResult)
                          }
                        }
                      ).start()
                    }
                    case r : Result => {
                      // 接收Worker的计算结果
                      // 1. 是不是所有的计算都已经完成？
                      //    需要知道使用了几个Worker？
                      //    所有的Worker都有结果返回？
                      //    使用独立的线程来监控所有使用的Worker的计算
                      // 2. 如果获取了所有的计算结果，该如何处理？
                      //    使用用户提交的计算逻辑将所有worker的结果进行合并
                      println(s"接收Worker[${r.port}]计算结果" + r.data)
                      // 3. 如何保存worker的计算结果

                      // 将 -1 变成 Worker的计算结果
                      workerResult(r.index) = r.data
                    }
                  }
                  client.close()
                }
              }
            ).start()
          }
        }
      }
    ).start()
  }

  startup()
}
