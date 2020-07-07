package com.tay.scala1

/**
 *
 * output:
 * name：taylor
 * ---------------------------------------------
 * name：taylor
 * childname：taylor
 *
 * @author karlieswift 
 *         date: 2020/6/29 17:32 
 *         ClassName: User3  
 * @version java "13.0.1"
 */
object User3 {

  def main(args: Array[String]): Unit = {
    new User3("taylor")
    println("---------------------------------------------")
    new User3Child("taylor")
  }
}

class User3(name:String){

  println("name："+name)
}

/**
 * User3Child(childname:String) extends User3(childname)
 *User3的参数直接来自User3Child的参数
 * @param childname
 */
class User3Child(childname:String) extends User3(childname) {
  println("childname："+childname)
}