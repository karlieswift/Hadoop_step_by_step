package com.tay.myGeneric

/**
 * @author karlieswift 
 *         date: 2020/7/6 14:04 
 *         ClassName: Generic1  
 * @version java "13.0.1"
 *
 *          Generic 泛型

 *          scala泛型的总结：
 *          三个类 Parent,User,Son,三者依次继承，一个测试类Test[T]
 *          对于  new Test[User]得到的对象：如下结论：
 *          1-Test[T]:User
 *          2-Test[+T]:User,Son
 *          3-Test[-T]:User,Parent
 *
 */
object Generic1 {

  def main(args: Array[String]): Unit = {
//   val t1:Test1[User1] = new Test1[Parent1]//error
//   val t2:Test1[User1] = new Test1[SubUser1]//error
    val t3:Test1[User1] = new Test1[User1]



  }
  
}
class Test1[T]{

}
class Parent1{

}

class User1 extends Parent1 {

}
class SubUser1 extends User1 {

}
