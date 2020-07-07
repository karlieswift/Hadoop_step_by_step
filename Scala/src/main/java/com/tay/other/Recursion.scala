package com.tay.other

/**
 * @author karlieswift 
 *         date: 2020/6/28 12:13 
 *         ClassName: Recursion  
 * @version java "13.0.1"
 */
object Recursion {

  def main(args: Array[String]): Unit = {
    /**
     * 递归计算
     * @return
     */
    def fun(n:Int): Int ={
      if(n==1) {
        return 1
      }
      n+fun(n-1)
    }


    def fun1(n:Int,result:Int): Int ={
      if(n==0) {
        return result
      }else{
      fun1(n-1,result+n)
      }
    }

    println(fun(3))
    println(fun1(3,0))
  }
}
