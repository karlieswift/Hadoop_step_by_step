package com.tay.mymatch

/**
 * @author karlieswift 
 *         date: 2020/7/5 13:50 
 *         ClassName: Match5  
 * @version java "13.0.1"
 *          // Scala - 模式匹配 - 偏函数
 *          // 以偏盖全
 *          // 偏 => 部分
 *          // 全 => 整体
 *          // 所谓的偏函数其实就是对满足条件的一部分数据进行处理的函数
 *          // 这里的全表示对所有的数据进行处理。
 *
 *          // 将该List(1,2,3,4,5,6,"test")中的Int类型的元素加一，并去掉字符串
 *          //  1. 集合中的Int数据加1
 *          //  2. 将字符串过滤掉
 *
 *
 */
object Match5 {


  def main(args: Array[String]): Unit = {

    val list: List[Any] = List(1,2,3,4,5,6,"test")



    /**
     * 方法一：会丢失数据
     */
    println(list.filter(list => {
      list.isInstanceOf[Int]
    }).map(_.asInstanceOf[Int] + 1))


    /**
     * 方法二：模式匹配方法-1
     */

    println(
     list.map(
       data =>{
         data match {
           case i:Int=>i+1
           case d=>d
         }
       }
     ).filter(data=>{
       data.isInstanceOf[Int]
     })
    )
    /**
     *采用偏函数的方式来实现需求
     * collect 采集方法可以传递一个偏函数实现功能
     * 使用case操作函数就是偏函数
     * 不是所有的函数都支持偏函数，如果函数的类型为PartialFunction，那么就是偏函数
     * 可以使用case语法操作
     */
    println(list.collect{
      case i:Int=> i+1
    })

  }
}
