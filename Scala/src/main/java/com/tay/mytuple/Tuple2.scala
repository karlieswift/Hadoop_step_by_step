package com.tay.mytuple

/**
 * @author karlieswift 
 *         date: 2020/7/1 14:21 
 *         ClassName: Tuple1  
 * @version java "13.0.1"
 *
 *          元组：元素组合
 */
object Tuple2 {


  def main(args: Array[String]): Unit = {
    var t=("a",2)  //对偶元组就是键值对map
    val map1 = Map(("a",1),("b",2))
    val map2 = Map("a"->1,"b"->2)

    println(map1) //Map(a -> 1, b -> 2)
    println(map2)  //Map(a -> 1, b -> 2)
    println(map1==map2) //true

    /**
     * 下面的k_v就是一个键值对，也就是对偶元组(k,v)只有2个元素
     *
     * output:
     * a:1
     * b:2
     */
    for(k_v <- map1){  //这里的k_v就是一个键值对，
      println(k_v._1+":"+k_v._2)
//      println(k_v)
    }


  }
}
