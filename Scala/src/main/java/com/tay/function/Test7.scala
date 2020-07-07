package com.tay.function

/**
 * @author karlieswift 
 *         date: 2020/6/26 14:20 
 *         ClassName: Test7  
 * @version java "13.0.1"
 */
object Test7 {
  def main(args: Array[String]): Unit = {

    def fun(f:(Int,Int)=>Int): Int ={
      f(10,20)
    }

    println(fun(  (x:Int,y:Int)=>{x*y}  )) //加入标准
    println(fun(  (x,y)=>{x*y}  ))//去掉类型
    println(fun(  (x,y)=>x*y  )) //去掉花括号
    println(fun( (_*_) )) //最简省略



  }


}
