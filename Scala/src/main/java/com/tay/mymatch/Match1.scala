package com.tay.mymatch

/**
 * @author karlieswift 
 *         date: 2020/7/4 10:58 
 *         ClassName: Match1  
 * @version java "13.0.1"
 */
object Match1 {

  def main(args: Array[String]): Unit = {

//    println(describel(false))
//    println(descibel1(Array(1,8," ")))
//    println(descibel1(true))
//    descibel2()

//    descibel3()
    descibel4()
  }

  /**
   * 1-匹配常量
   * @param x
   * @return
   */
  def describel(x: Any) = x match {
    case 5 => "Int five"
    case "hello" => "String hello"
    case true => "Boolean true"
    case '+' => "Char +"
    case _=>  "anything"
  }

  /**
   * 2-匹配类型
   * @param x
   * @return
   */
  def descibel1(x:Any): String ={
    x match{
      case i: Int => "Int"
      case s: String => "String hello"
      case m: List[_] => "List"
      case c: Array[Int] => "Array[Int]"
      case b:Boolean=>"boolean"
      case someThing => "something else " + someThing

    }

  }
  /**
   * 匹配数组
   */

  def descibel2(): Unit ={
    for (elem <- Array(Array(1), Array(11),Array(1, 2, 3), Array("str", "taylor"))) {
      var t=elem match {
        case Array(1)=>"1"
        case Array(x)=>"数组长度为1"
        case Array(1,_*)=>"1开头，后面任意"
        case _=>"anything"
      }
      println(t)
    }
  }


  /**
   * 匹配列表
   */

  def descibel3(){
    for (list <- Array(List(0), List(1, 0), List(0, 0, 0), List(1, 0, 0), List(88))){
      var t=list match {
        case List(1)=>"1"
        case List(x)=>"数组长度为1"
        case List(1,_*)=>"1开头，后面任意"
        case _=>"anything"
      }
      println(t)
    }

  }

  /**
   * 匹配元组
   */

  def descibel4(){
    for (tuple <- Array((0, 1), (1, 0), (1, 1), (1, 0, 2))) {
      var t=tuple match {
        case (x,0)=>" 匹配后一个元素是0的对偶元组"
        case (x,y)=>""+x+" "+y+""
        case _=>"anything"
      }
      println(t)
    }
  }


}


