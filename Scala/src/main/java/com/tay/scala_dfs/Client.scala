package com.tay.scala_dfs

import java.io.ObjectOutputStream
import java.net.Socket

import com.tay.scala_dfs.bean.Data


/**
 * @author karlieswift 
 *         date: 2020/7/7 8:43 
 *         ClassName: Client  
 * @version java "13.0.1"
 */
object Client extends App {

  // TODO 1. 准备数据：数据，计算功能, 需要计算节点的数量
  var data = new Data()
  data.data = List(100,11,23,21,2,6)
  data.logic = (x:Int, y:Int) => { x * y}
  data.workerCount = 3

  // TODO 2. 建立和Master的连接
  val masterConnection = new Socket("localhost", 9999)

  // TODO 3. 向Master发送数据
  val objOut = new ObjectOutputStream(masterConnection.getOutputStream)
  objOut.writeObject(data)
  objOut.flush()
  masterConnection.close()
  println("用户提交作业完毕")
}