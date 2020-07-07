package com.tay.my_regular_expression

import scala.util.matching.Regex

/**
 * @author karlieswift 
 *         date: 2020/7/6 14:38 
 *         ClassName: Regular_Expression1  
 * @version java "13.0.1"
 */
object Regular_Expression1 {

  def main(args: Array[String]): Unit = {
    //声明规则
    val r: Regex = "s".r
    //准备数据
    val s="scalas"
    val maybeString: Option[String] = r.findFirstIn(s)
    //使用规则
    if(maybeString.isEmpty){
      println("no find")
    }
    else{
      println(maybeString)
    }
  }
}
