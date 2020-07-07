package com.tay.scala_dfs.bean

/**
 * @author karlieswift 
 *         date: 2020/7/7 8:45 
 *         ClassName: Task  
 * @version java "13.0.1"
 */
case class Task() {

  var data :List[Int]= _
  var logic :(Int,Int)=>Int =_

  var  index:Int=_

  def compute(): Int ={
    data.reduce(logic)
  }

}
