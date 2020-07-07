package com.tay.myabstract

/**
 * 抽象
 * @author karlieswift 
 *         date: 2020/6/30 8:59 
 *         ClassName: MyAbstract1  
 * @version java "13.0.1"
 */
object MyAbstract1 {


  def main(args: Array[String]): Unit = {

    val user = new ChildUser()
    user.test1()
  }



  abstract class User{
    //类名需要加上abstract关键字
    //方法不需要加上abstrat关键字
    def test()
    def test1(): Unit ={
      println("user")
    }

  }

  class ChildUser extends User{
    def test(): Unit = {

    }


    override def test1(): Unit ={
      println("ChildUser")
    }
  }




}


