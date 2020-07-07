package com.tay.collectionMethod

/**
 * @author karlieswift 
 *         date: 2020/7/1 15:00 
 *         ClassName: CollectionMethod1  
 * @version java "13.0.1"
 *
 *          集合到的常用的方法
 *          1-长度
 *          2-包含
 *          3-去除，获取元素
 *          4-去重
 *          5-查找元素
 */
object CollectionMethod1 {


  def main(args: Array[String]): Unit = {

    val list = List("taylor","karlie","swift","Kloss","gais","sophie","Marceau")
    //长度
//    println(list.length)
//    println(list.size)
//    //是否存在
//    println(list.contains(1))


    /**
     * 去除，取出n个元素
     */
//    println(list.take(2)) //从左边边去两个元素  List(taylor, karlie)
//    println(list.takeRight(2)) //从右边去两个元素  List(sophie, Marceau)
//    println(list.drop(2)) //去除 List(swift, Kloss, gais, sophie, Marceau)
//    println(list.dropRight(2)) //List(taylor, karlie, swift, Kloss, gais)
//    println(list.reverse) //List(Marceau, sophie, gais, Kloss, swift, karlie, taylor)


    /**
     * 去重
     */

    val ls = List(6,1,2,3,1)
    println(ls.distinct) //List(6, 1, 2, 3)
    println(ls)//List(6, 1, 2, 3, 1)


//    println(list.find(p=>p.contains("e")))
//    println(ls.find(p=> p%2==0 ))




  }
}
