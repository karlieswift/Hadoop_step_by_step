package com.tay.myArray

/**
 *
 * @author karlieswift 
 *         date: 2020/6/30 16:44 
 *         ClassName: Array2  
 * @version java "13.0.1"
 *
 *
 *
 *          :+ 与+：谁离:近，谁在前边
 */
object Array2 {

  def main(args: Array[String]): Unit = {
    val array1=Array(1,2,3,4)
    val array2=Array(5,6,7,8)
    println(array1.mkString(" "))

    //末尾添加数据,产生新的集合
//    val array3=array1.:+(5)
    val array3=array1:+(5)
    println(array3.mkString(" "))
    println(array1 eq array3)


    //第一个位置添加数据,产生新的集合
    //如果运算符以冒号结尾，那么运算规则从右向左运算
//    val array4=array1.+:(6)
    val array4=(6)+: array1
    println(array4.mkString(" "))

    println("--------------------------------------------")
    //遍历1
    def myprint(i:Int): Unit ={
      print(i+" ")
    }
    array1.foreach(myprint)
    println("--------------------------------------------")
    array1.foreach((i:Int)=>{print(i)})
  }
}
