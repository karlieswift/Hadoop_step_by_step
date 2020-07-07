package com.tay.mytrait

/**
 *trait:特质，类似于java的interface
 * trait:特点;特色;特性;一点点,少许,微量
 *
 *
 * User2Trait extends User2Parent with User2_2 with User2_1 {
 *
 * 此时的super是调用with最右边的类User2_1
 * }
 * @author karlieswift 
 *         date: 2020/6/30 10:39 
 *         ClassName: MyTrait1  
 * @version java "13.0.1"
 */
object MyTrait2 {

  def main(args: Array[String]): Unit = {


    var t:User2Trait=new User2Trait()
    t.run()

  }


  /**
   * 反编译：其实trait就是interface
   * public interface User1 {
   * }
   */
  trait  User2_1{
    def run(): Unit ={
      println("User2_1")
    }

  }
  trait  User2_2{
    def run(): Unit ={
      println("User2_2")
    }

  }

  class User2Parent{
    def run(): Unit ={
      println("User2Parent")
    }
  }
  class User2Trait extends User2Parent with User2_2 with User2_1 {
    override def run(): Unit = {
      println("User2Trait")
      super.run()
    }
  }
}
