package com.tay.myArray

/**
 *
 *
 * @author karlieswift 
 *         date: 2020/6/30 16:10 
 *         ClassName: Array1  
 * @version java "13.0.1"
 *
 *
 *  声明数组的方法
 *  1-Array(1,3,32,2,1)
 *  2-new Array[Int](3)
 *
 *
 */
object Array1 {

  def main(args: Array[String]): Unit = {


    var arr1=Array(1,2,3,4,5,6)
    println(arr1.length)
    println(arr1(0))
    //同时加入不同类型
    var arr2=Array(1,"taylor",'A',new Person())
    //修改数据的两种方法
    arr2.update(1,2)
    arr2(1)="taylorswift"

    //遍历方法一
    for (elem <- arr2) {
      print(elem+" ")
    }
    println()

    //遍历方法二
    println(arr2.mkString(":"))


    var arr=new Array[Int](3) //创建一个大小int为3的数组


    arr.update(0,1)  //更新
    println(arr.apply(0))//获取0索引的值
    //遍历
    for (elem <- arr) {
      print(elem+" ")
    }
  }
}
class Person{

}