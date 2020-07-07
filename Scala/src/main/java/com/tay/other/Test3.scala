package com.tay.other

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/4 9:57 
 *         ClassName: Test3  
 * @version java "13.0.1"
 */
object Test3 {

  def main(args: Array[String]): Unit = {
    val ls = List(
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "电脑"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "电脑"),
      ("zhangsan", "河南", "电脑"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子")
    )

    var ls1 = ls.map(ls => {
      (ls._1, (ls._2, ls._3))
    }).groupBy(_._2._1)

    for (elem <- ls1) {
     val k = elem._2
      println(k.groupBy(ls => ls._2).toList.map(ls=>{
        (ls._1,ls._2.size)
      }).sortBy(-_._2)
      )
    }



  }
}
