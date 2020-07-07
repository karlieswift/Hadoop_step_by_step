package com.tay.socket2

import java.io.Serializable
;

/**
 * @author karlieswift 
 *         date: 2020/6/28 15:21 
 *         ClassName: Task  
 * @version java "13.0.1"
 */
class Task extends Serializable {

  private val data=10
  private def fun(data:Int)=data*2
  def compute=fun(data)

}
