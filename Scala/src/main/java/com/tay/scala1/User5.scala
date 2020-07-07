package com.tay.scala1
import com.tay.objectTest.User2
/**
 * @author karlieswift 
 *         date: 2020/6/29 19:14 
 *         ClassName: User5  
 * @version java "13.0.1"
 */
object User5 {
  def main(args: Array[String]): Unit = {

  }
}
class User5 {
  def fun(): Unit ={
    new com.tay.objectTest.User2().public_name
    new com.tay.objectTest.User2().deafault_name
  }
}
class User5Child extends com.tay.objectTest.User2{
  new User5Child().deafault_name
  new User5Child().protected_name
  new User5Child().public_name
}