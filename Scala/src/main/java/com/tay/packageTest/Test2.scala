package com.tay.packageTest

//import com.tay.packageTest.A.B.C.DD
//"_"类似于java里的"*",代表全部
import com.tay.packageTest.A.B.C._
/**
 * @author karlieswift 
 *         date: 2020/6/28 16:37 
 *         ClassName: Test2  
 * @version java "13.0.1"
 */
object Test2 {

  def main(args: Array[String]): Unit = {
    new DD().fun()

  }
}
