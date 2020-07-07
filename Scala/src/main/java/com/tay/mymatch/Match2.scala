package com.tay.mymatch

/**
 * @author karlieswift 
 *         date: 2020/7/4 11:49 
 *         ClassName: Match2  
 * @version java "13.0.1"
 *          对象匹配
 */
object Match2 {

  def main(args: Array[String]): Unit = {

    val person = Person("taylor",21)
    var result= person match {
      case Person("taylor",21)=>"yes"
      case _=> "no"
    }

    println(result)
  }
}

case class  Person( var name:String,var age:Int){

}