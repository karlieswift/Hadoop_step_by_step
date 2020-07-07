package com.tay.scala1

/**
 * @author karlieswift 
 *         date: 2020/6/29 18:06 
 *         ClassName: SaclaApply  
 * @version java "13.0.1"
 */
object SaclaApply {

  def apply(): java.util.Date= new java.util.Date()

  def apply(i:Int): SaclaApply = new SaclaApply()

  def main(args: Array[String]): Unit = {

    //伴生对象的apply
//    val apply1 =SaclaApply //省略() 则返回的是object SaclaApply
//    println(apply1)//com.tay.scala1.SaclaApply$@7f560810
//    val apply2 =SaclaApply()
//    println(apply2)//Mon Jun 29 18:59:27 CST 2020
//    val apply3 = SaclaApply(1)
//    println(apply3)//com.tay.scala1.SaclaApply@7c0e2abd


    //伴生类的apply
    val apply4 = new SaclaApply()
    val a1 = apply4.apply()
    println(a1)
    val a2 = apply4()
    println(a2)

  }

}
class SaclaApply{
  def apply(): SaclaApply =
    {
      println("haha")
      new SaclaApply()
    }
}