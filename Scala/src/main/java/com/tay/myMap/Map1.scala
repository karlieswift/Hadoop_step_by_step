package com.tay.myMap

/**
 * @author karlieswift 
 *         date: 2020/7/1 12:20 
 *         ClassName: Mao1  
 * @version java "13.0.1"
 *
 *
 *          不可变map
 */
object Map1 {


  def main(args: Array[String]): Unit = {
    //map 的key-value 可以为任意类型
    val map1 = Map(1->"taylor",new Person->"karlie","3"->(666,999),true->"haha")

    println(map1.size)
    println(map1)
  }
}
class Person{

}