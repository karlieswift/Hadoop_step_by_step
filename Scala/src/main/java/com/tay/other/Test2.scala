package com.tay.other

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/4 8:35 
 *         ClassName: Test2  
 * @version java "13.0.1"
 */
object Test2 {


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
    val map1 = ls.groupBy(_._2)


    val map3: mutable.Map[String, Int] = mutable.Map()
    for (elem <- map1) {
      val value = elem._2

      for (elem1 <- value) {
        val value1 = elem1._1
        if (map3.contains(elem1._2 + elem1._3))
          map3.update(elem1._2 + elem1._3, map3.getOrElse(elem1._2 + elem1._3, 0) + 1)
        else {
          map3.update(elem1._2 + elem1._3, 1)
        }
      }
    }

    println(map3.toList.sortBy(-_._2))


  }
}
