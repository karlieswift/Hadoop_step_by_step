package com.tay.collectionMethod

/**
 * @author karlieswift 
 *         date: 2020/7/2 16:09 
 *         ClassName: CollectionMethod2  
 * @version java "13.0.1"
 *
 *          1-取head，tail
 *
 */
object CollectionMethod2 {


  def main(args: Array[String]): Unit = {
    val ls1 = List(1,2,3,4)

    /**
     * 查看head，tail，last 广义表的知识点
     */
//    println(ls1.head) //1
//    println(ls1.tail) //List(2, 3, 4)
//    println(ls1.last) //4
//      println(ls1.tails.mkString(" "))  //List(1, 2, 3, 4) List(2, 3, 4) List(3, 4) List(4) List()
    val tails = ls1.tails
    while(tails.hasNext){
      println(tails.next().mkString(" "))
    }
    /**
    1 2 3 4
    2 3 4
    3 4
    4
     */




  }
}
