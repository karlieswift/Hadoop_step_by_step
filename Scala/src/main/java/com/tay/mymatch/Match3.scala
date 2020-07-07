package com.tay.mymatch

/**
 * @author karlieswift 
 *         date: 2020/7/4 11:49 
 *         ClassName: Match2  
 * @version java "13.0.1"
 *          对象匹配
 */
object Match3 {

  def main(args: Array[String]): Unit = {

    val person = Person1("taylor",21)
    var result= person match {
      case Person1("taylor",21)=>"yes"
      case _=> "no"
    }

    println(result)
  }
}

object Person1{
  def apply(name: String, age: Int): Person1 = new Person1(name, age)

  def unapply(p1: Person1): Option[(String, Int)] = {
    if(Person1==null){
      None
    }
    else{
      Some(p1.name,p1.age)
    }
  }
}
 class  Person1( var name:String,var age:Int){

}