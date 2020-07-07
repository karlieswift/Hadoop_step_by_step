package com.tay.myGeneric

/**
 * @author karlieswift 
 *         date: 2020/7/6 14:22 
 *         ClassName: Generic2  
 * @version java "13.0.1"
 *
 *          泛型协变
 */
object Generic2 {

  def main(args: Array[String]): Unit = {
//       val t1:Test2[User2] = new Test2[Parent2]//error
       val t2:Test2[User2] = new Test2[SubUser2]
       val t3:Test2[User2] = new Test2[User2]



  }

}
class Test2[+T]{

}
class Parent2{

}

class User2 extends Parent2 {

}
class SubUser2 extends User2 {

}
