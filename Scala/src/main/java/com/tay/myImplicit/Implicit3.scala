package com.tay.myImplicit

/**
 * @author karlieswift 
 *         date: 2020/7/6 9:38 
 *         ClassName: Implicit3  
 * @version java "13.0.1"
 *
 *          所谓的隐式机制，就是一旦出现编译错误时，编译器会从哪些地方查找对应的隐式转换规则
 *          当前代码作用域
 *          当前代码上级作用域
 *          当前类所在的包对象
 *          当前类（对象）的父类（父类）或特质（父特质）
 *          其实最直接的方式就是直接导入。
 *
 *
 *
 *          需求：
 *          添加一个add函数，在不改变原来Implicit3Test1的基础上，不改变原来的逻辑代码
 *
 *          添加一个add函数的三种方法
 *          1-在Implicit3Test1类里加入一个add函数----会改变Implicit3Test1 不推荐
 *          2-通过trait特质，重写写一个类Implicit3Test2，然后在Implicit3Test2 添加一个add函数，在原来的
 *          逻辑的代码里val test = new Implicit3Test1 with Implicit3Test2  动态混入，但改变了原来的逻辑代码
 *          3- 通过implicit 关键字 实现implicit class Implicit3Test3
 */
object Implicit3 {

  def main(args: Array[String]): Unit = {
//    val test = new Implicit3Test1 with Implicit3Test2   //方法2改变了原来的逻辑
    val test = new Implicit3Test1


    test.insert()
    test.add()

  }
  /**
   * 方法三
   * 在不改变原来Implicit3Test1的基础上，不改变原来的逻辑代码
   * @param implicit3Test1
   */
  implicit class Implicit3Test3(implicit3Test1: Implicit3Test1){
    def add(): Unit ={
      println("add to")
    }
  }


}
class Implicit3Test1{
  def insert(): Unit ={
    println("insert into")
  }

  //方法一：修改代码，，不推荐
//  def add(): Unit ={
//    println("add to")
//  }
}

//方法二
trait Implicit3Test2{
  def add(): Unit ={
    println("add to")
  }


}


