package com.tay.myMap

/**
 * @author karlieswift 
 *         date: 2020/7/1 12:25 
 *         ClassName: Map2  
 * @version java "13.0.1"
 */
object Map2 {


  def main(args: Array[String]): Unit = {
    val map1 = Map("a"->1,"b"->2)
    val map2 = Map("c"->3,"d"->4)

    //添加更新集合
    println(map1+("f"->3)) //Map(a -> 1, b -> 2, f -> 3)
    println(map1+("a"->3)) //Map(a -> 3, b -> 2)
    //删除key
    println(map1-"a") //Map(b -> 2) 不影响原来的集合map
//    println(map1) //Map(a -> 1, b -> 2)

    println(map1++map2) //Map(a -> 1, b -> 2, c -> 3, d -> 4)


    //重复的key 会更新
    val map3 = Map("a"->6,"c"->3,"d"->4)
    println(map1++map3)  //Map(a -> 6, b -> 2, c -> 3, d -> 4)
  }
}
