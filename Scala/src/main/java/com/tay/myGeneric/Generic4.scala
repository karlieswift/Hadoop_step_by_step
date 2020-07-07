package com.tay.myGeneric

import com.tay.scala1.User4

/**
 * @author karlieswift 
 *         date: 2020/7/6 14:22 
 *         ClassName: Generic2  
 * @version java "13.0.1"
 *
 *          一：java里的泛型上下限：
 *          先看代码：
 *          public class TestGeneric {
 *          public static void main(String[] args) {
 *          test1(C.class);
 *          test1(B.class);
 *          //        test1(A.class);//出错
 *          test2(A.class);
 *          test2(B.class);
 *          //        test2(C.class);//出错
 *          }
 *          public static void test1(Class<? extends B> c){ }
 *          public static void test2(Class<? super B> c){ }
 *          }
 *          class A{ }
 *          class B extends A{ }
 *          class C extends B{ }
 *          java泛型总结：java里采用 extends 和 super 关键字 的形式进行限定
 *          1--<? extends B> :表示 class C extends B 关系时(C继承B)，类C，B可以用
 *          2--<? super B> :表示 class B extends A 关系时(B继承A)，类A，B可以用
 *
 *
 *
 *          二：scala的泛型有 1-泛型不可变，2-泛型协变，3-泛型逆变
 *          1-泛型不可变，2-泛型协变，3-泛型逆变
 *          scala泛型的总结：
 *          三个类 Parent,User,Son,三者依次继承，一个测试类Test[T]
 *          对于  new Test[User]得到的对象：如下结论：
 *          1-Test[T]:User  1-泛型不可变
 *          2-Test[+T]:User,Son  2-泛型协变  这就类似 java里的 User u=new Son() 理所当然， User u=new Parent()就会报错
 *          3-Test[-T]:User,Parent  3-泛型逆变 这就类似 java里的 User u=new Parent()会报错，在scala的泛型加上逆变[-T]，就可以
 *                   实现  val t1:Test[User] = new Test[Parent] 具体看下面代码
 *
 *          代码如下：
 *          object Generic1 {
 *          def main(args: Array[String]): Unit = {
 *           val t1:Test[User] = new Test[Parent]
 *          //   val t2:Test[User] = new Test[Son]//error
 *          val t3:Test1[User] = new Test1[User]
 *            }
 *          }
 *         // class Test[T]{} //1-泛型不可变
 *          //class Test[+T]{}// 2-泛型协变
 *           class Test[-T]{}//3-泛型逆变
 *          class Parent{}
 *          class User extends Parent {}
 *          class Son extends User {}
 *
 *
 *          三：scala里的泛型上下限
 *          scala的泛型上下限 采用大于小于号(> <)来表示，更直观
 *          1-- def test1[ e <:E](p:e){}:表示 只要是E,以及E的子类都可以
 *          2--def test2[ e >:E](p:e){}:表示 只要是E,以及E的父类都可以
 *
 *
 */
object Generic3 {
  def main(args: Array[String]): Unit = {
    test1[E](new E)
    test1[F](new F)
//    test1[D](new D) //运行时会报错
    test2[E](new E)
//    test2[F](new F)//运行时会报错
    test2[D](new D)
  }
  def test1[ e <:E](p:e): Unit ={
  }
  def test2[ e >:E](p:e): Unit ={
  }
}
class D{}
class E extends D {}
class F extends E {}
