package com.tay.myArray

/**
 * @author karlieswift 
 *         date: 2020/6/30 17:07 
 *         ClassName: ArrayTRaverse  
 * @version java "13.0.1"
 *
 *
 *  集合的几种遍历方式
 */
object ArrayTRaverse {

  def main(args: Array[String]): Unit = {
    val array=Array(1,2,3,4,5,6)

    //方法1
    for (elem <- array) {
      print(elem+" ")
    }
    println()
    println("--------------------------------------------")

    //方法2
    println(array.mkString(" "))
    println("--------------------------------------------")


    //方法3
//    def mytraverse(i:Int): Unit ={
//      print(i+" ")
//    }
//    array.foreach(mytraverse)
//    array.foreach((i:Int)=>{print(i+" ")})
//    array.foreach((i:Int)=>print(i+" "))
//    array.foreach((i)=>print(i+" "))
//    array.foreach(println(_))
      array.foreach(println)


  }



}
