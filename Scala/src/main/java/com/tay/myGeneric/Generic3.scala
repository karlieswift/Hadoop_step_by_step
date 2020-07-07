package com.tay.myGeneric

/**
 * @author karlieswift 
 *         date: 2020/7/6 14:22 
 *         ClassName: Generic2  
 * @version java "13.0.1"
 *
 *          泛型逆变
 */
object Generic3 {

  def main(args: Array[String]): Unit = {
       val t1:Test3[User3] = new Test3[Parent3]
//       val t2:Test3[User3] = new Test3[SubUser2]//error
       val t3:Test3[User3] = new Test3[User3]



  }

}
class Test3[-T]{

}
class Parent3{

}

class User3 extends Parent3 {

}
class SubUser3 extends User3 {

}
