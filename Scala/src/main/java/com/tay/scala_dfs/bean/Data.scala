package com.tay.scala_dfs.bean

/**
 * @author karlieswift 
 *         date: 2020/7/7 8:44 
 *         ClassName: Data  
 * @version java "13.0.1"
 */
case class Data() {

  var workerCount : Int=_
  var data :List[Int]= _
  var logic :(Int,Int)=>Int =_

}
