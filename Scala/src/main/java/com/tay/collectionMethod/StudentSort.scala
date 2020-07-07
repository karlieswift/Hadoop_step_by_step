package com.tay.collectionMethod

/**
 * @author karlieswift 
 *         date: 2020/7/2 18:14 
 *         ClassName: StudentSort  
 * @version java "13.0.1"
 *          自定义排序
 *          实现学生排序：先按math排序，再按english排序
 *
 *
 *          output:
 *          学生列表
 *          Student(taylor, 133, 132)
 *          Student(karlie, 133, 122)
 *          Student(swift, 113, 129)
 *          Student(kloss, 121, 136)
 *          排序的学生列表
 *          Student(taylor, 133, 132)
 *          Student(karlie, 133, 122)
 *          Student(kloss, 121, 136)
 *          Student(swift, 113, 129)
 *
 */
object StudentSort {

  def main(args: Array[String]): Unit = {
    val sd1 = new Student("taylor",133,132)
    val sd4 = new Student("kloss",121,136)
    val sd3 = new Student("swift",113,129)
    val sd2 = new Student("karlie",133,122)
    val students = List(sd1,sd2,sd3,sd4)
    println("学生列表")
    show(students)
    val studentsort=students.sortWith(
      (left,right)=>{
        if(left.math!=right.math){
          left.math>right.math
        }else{
          left.english>right.english
        }
    })

    println("排序的学生列表")
    show(studentsort)
  }


  def show(list: List[Student]): Unit ={
    for (elem <- list) {
      println(elem.toString)
    }
  }
}


class Student{
  var name :String=_
  var math :Int=_
  var english :Int=_

  def this(name:String,math:Int,english:Int){
    this()
    this.name=name
    this.math=math
    this.english=english

  }
  override def toString = s"Student($name, $math, $english)"
}