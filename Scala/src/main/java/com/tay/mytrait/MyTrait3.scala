package com.tay.mytrait

/**
 *
 * 类加载是先加载extends的类，with的类从左往右加载
 * 类加载：总体从左往右
 * @author karlieswift 
 *         date: 2020/6/30 12:09 
 *         ClassName: MyTrait3  
 * @version java "13.0.1"
 */
object MyTrait3 {
  def main(args: Array[String]): Unit = {
    val user3Trait = new User3Trait()
  }

  trait  User3_1{
    println("User3_1")

  }
  trait  User3_2{
    println("User3_2")

  }

  class User3Parent{
    println("User3Parent")

  }
  class User3Trait extends User3Parent with User3_2 with User3_1 {
    println("User3Trait")

  }


}
