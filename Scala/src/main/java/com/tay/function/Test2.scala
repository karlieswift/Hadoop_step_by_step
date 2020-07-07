package com.tay.function

/**
 * 参数测试
 * @author karlieswift 
 *         date: 2020/6/26 12:26 
 *         ClassName: Test2  
 * @version java "13.0.1"
 */
object Test2 {
  def main(args: Array[String]): Unit = {


    /**
    传入多个参数
     */
    def test1(string: String*): Unit ={
      for(i<-Range(0,string.length)){
        println(string.apply(i))
      }
    }
//    test1("taylor","karlie","kloss")


    def test2(length:Int ,string: String*): Unit ={
      for(i<-Range(0,length)){
        println(string.apply(i))
      }

    }
//    test2(length=3,"taylor","karlie","kloss")

    /**
     * 默认参数
     */

    def test3(name:String,password:String="12356"): Unit ={
      println("your name is "+name+", password is "+password)
    }

//    test3("taylorswift")
//    test3("taylorswift","hello")
//    test3("taylorswift",null)
//    test3(name = "karlie",password = "666666")




  }

}
