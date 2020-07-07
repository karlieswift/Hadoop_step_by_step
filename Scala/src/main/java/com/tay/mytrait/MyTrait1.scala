package com.tay.mytrait

/**
 *trait:特质，类似于java的interface
 * trait:特点;特色;特性;一点点,少许,微量
 * @author karlieswift 
 *         date: 2020/6/30 10:39 
 *         ClassName: MyTrait1  
 * @version java "13.0.1"
 */
object MyTrait1 {

  def main(args: Array[String]): Unit = {


    var t:User1Trait=new User1Trait()
    t.run()
  }


  /**
   * 反编译：其实trait就是interface
   * public interface User1 {
   * }
   */
  trait  User1{

    def run()

  }
  class User1Trait extends User1{
    override def run(): Unit = {
      println("run.............")
    }
  }
}
