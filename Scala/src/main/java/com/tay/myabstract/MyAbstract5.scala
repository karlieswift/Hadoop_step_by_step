package com.tay.myabstract

/**
 * @author karlieswift 
 *         date: 2020/6/30 9:56 
 *         ClassName: MyAbstract5  
 * @version java "13.0.1"
 */
object MyAbstract5 {
  def main(args: Array[String]): Unit = {

    new User5Child() //0
  }

  abstract class User5{

    val age:Int=20
    println(age)

  }
  class User5Child extends User5 {
    override val age:Int=30

  }
}
