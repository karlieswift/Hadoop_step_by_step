package com.tay.other

import scala.collection.mutable
/**
 * @author karlieswift 
 *         date: 2020/7/3 15:45 
 *         ClassName: MapCombine  
 * @version java "13.0.1"
 *
 *          output:
 *          List((a,5), (b,2), (c,6), (d,6))
 *          List((a,5), (b,2), (c,6), (d,6))
 *          Map(Hello -> 6, Spark -> 2, Scala -> 4)
 */
object MapCombine {



  def main(args: Array[String]): Unit = {


    fun1()
    fun2()
    fun3()


  }
  def fun1(): Unit ={
    var map1 = mutable.Map("a"->2,"b"->2,"c"->3)
    val map2 = mutable.Map("a"->3,"c"->3,"d"->6)
    for (elem <- map2) {
      var k=elem._1
      var v=elem._2
      if(map1.get(k)==null){
        map1=map1+(k->v)
      }else{
        map1.update(k,v+map1.getOrElse(k,0))
      }
    }
    println(map1.toList.sortBy(_._1))
  }


  def fun2(): Unit ={
    var map1 = mutable.Map("a"->2,"b"->2,"c"->3)
    val map2 = mutable.Map("a"->3,"c"->3,"d"->6)
    println(map1.foldLeft(map2)(
      (map1,map2)=>{
        val k = map2._1
        val v = map2._2
        val i = map1.getOrElse(k,0)
        map1.update(k,v+i)
        map1
      }
    ).toList.sortBy(_._1)
    )

    }


  def fun3(): Unit ={

    val dataList = List(
      ("Hello Scala", 4), ("Hello Spark", 2)
    )
    val map:mutable.Map[String,Int] = mutable.Map()
    for (elem <- dataList) {
      val strings = elem._1.split(" ")

      strings.foreach(
        str=>{
          var i=map.getOrElse(str,0)
          map.update(str,elem._2+i)
        }
      )
    }

    println(map)

  }
}
