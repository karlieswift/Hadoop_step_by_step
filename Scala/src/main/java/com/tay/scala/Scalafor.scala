package com.tay.scala

/**
 * @author karlieswift 
 *         date: 2020/6/23 16:45 
 *         ClassName: Scalafor  
 * @version java "13.0.1"
 */
object Scalafor {

  def main(args: Array[String]): Unit = {
    /**
     * 循环打印1-6
     */
    //    for (i:Int <- 1 to 6){
//      println(i)
//    }
//    for (i <- 1 to 6) {
//      println(i)
//    }

//    for (i <- Range(1,7)){
//      println(i)
//    }

//    for (i <- 1 until 7){
//      println(i)
//    }
    /**
     * 打印6-1
     */
//    for( i<- Range(6,0,-1)){
//      println(i)
//    }



    for( i<- Range(1,10,1) if(i%2==1)){
      println(i)
    }
  }

}
