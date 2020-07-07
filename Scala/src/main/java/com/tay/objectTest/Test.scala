package com.tay.objectTest

/**
 * @author karlieswift 
 *         date: 2020/6/28 15:40 
 *         ClassName: Person1  
 * @version java "13.0.1"
 */
object Test {
  def main(args: Array[String]): Unit = {


    val person = new Person1
    person.age=22
    person.name="taylor"

    person.showinfo()
    new Student().showinfo()

  }



}
class Person1{
  var name:String=""
  var age:Int=0
 private[objectTest] var name_protect:String=""

  def showinfo(): Unit ={
    println(name+":"+age)
  }
}


class Student extends Person1{

}

