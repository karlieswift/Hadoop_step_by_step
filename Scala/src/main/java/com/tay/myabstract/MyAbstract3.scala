package com.tay.myabstract

/**
 * 重写的变量必须，加上override val 两个关键字
 * @author karlieswift 
 *         date: 2020/6/30 9:25 
 *         ClassName: MyAbstract3  
 * @version java "13.0.1"
 */
object MyAbstract3 {

  def main(args: Array[String]): Unit = {

    new User3Child().fun()
  }

   class User3{

    val name:String="taylor"

    def fun(): Unit ={
      println(name)
    }
  }
  class User3Child extends User3 {

     override val name:String="karlie" //重写的变量必须，加上override val 两个关键字
     println("---------")
  }

}
