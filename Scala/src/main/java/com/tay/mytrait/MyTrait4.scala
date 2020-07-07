package com.tay.mytrait

/**
 *
 * @author karlieswift
 *         date: 2020/6/30 14:31
 *         ClassName: MyTrait4
 * @version java "13.0.1"
 *
 *
 *
 * output:
 * 操作数据null 向数据库中null 向log中null 向File中File
 * 向File中fun 向log中fun 向数据库中fun 操作数据fun
 *
 * 总结：
 * scala 类似于多继承，通过trait 声明，with连接
 * 例如：  class MySQL extends DB with Log with File {
 *                                 }
 * 1-类加载是从左往右  --->
 *        -------当多个子类对父类的同一个变量进行重写时，重写的变量必须，加上override val 两个关键字
 *        -------类加载时在只有最后继承的(with的类)一个类，才真正的进行变量赋值，其他都没有进行赋值，
 *        -------也就是说，为null 或者为 0  (就是上面output结果里到的null ,只有最后一个File类打印了file)
 * 2-函数加载从右往左  <---
 *
 */
object MyTrait4 {
  def main(args: Array[String]): Unit = {
    val mysql: MySQL = new MySQL
    println()
    mysql.operData()
  }
  trait Operate {
    val name="Operate"
    print(s"操作数据$name ")
    def operData(): Unit = {
      println("操作数据fun ")
    }
  }

  trait DB extends Operate {
    override val name="DB"  //重写的变量必须，加上override val 两个关键字
    print(s"向数据库中$name ")
    override def operData(): Unit = {
      print("向数据库中fun ")
      super.operData()
    }
  }

  trait Log extends Operate {
    override val name="Log"   //重写的变量必须，加上override val 两个关键字
    print(s"向log中$name ")
    override def operData(): Unit = {
      print("向log中fun ")
      super.operData()
    }
  }

  trait File extends Operate {
    override val name="File"  //重写的变量必须，加上override val 两个关键字
    print(s"向File中$name ")
    override def operData(): Unit = {
      print("向File中fun ")
      super.operData()
    }
  }
  class MySQL extends DB with Log with File {
//  print(s"向MySQL中$name")
  }

}
