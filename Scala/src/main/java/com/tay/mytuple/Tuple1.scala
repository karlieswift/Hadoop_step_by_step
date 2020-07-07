package com.tay.mytuple

/**
 * @author karlieswift 
 *         date: 2020/7/1 14:21 
 *         ClassName: Tuple1  
 * @version java "13.0.1"
 *
 *          元组：元素组合
 *          在Scala语言中，我们可以将多个无关的数据元素封装为一个整体，
 *          这个整体我们称之为：元素组合，简称元组。有时也可将元组看成容纳元素的容器，其中最多只能容纳22个
 */
object Tuple1 {


  def main(args: Array[String]): Unit = {
    var tuple1=(1.2,"taylor",2,true)
    //元组最多能放22个
//    println(tuple1)

//    println(tuple1._1)
//    println(tuple1._2)
//    println(tuple1._3)
//    println(tuple1._4)


//    val iterator = tuple1.productIterator
//
//    while (iterator.hasNext){
//      println(iterator.next())
//    }

    for(i<-Range(0,4)){

      println(tuple1.productElement(i))
    }


  }
}
