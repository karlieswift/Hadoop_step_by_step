package com.tay.sparkcore.other

/**
 * @author karlieswift 
 *         date: 2020/7/9 12:18
 *         ClassName: ThreadLocalTest
 * @version java "13.0.1"
 */
object ThreadLocalTest {


  private val value = new ThreadLocal[String]()
  def main(args: Array[String]): Unit = {
    value.set("taylor")
    controler()
  }

  def controler(): Unit ={
    deal()
  }
  def deal(): Unit ={
    dao()
  }
  def dao(): Unit ={
    val str: String = value.get()
    println(str)
  }
}
