package com.tay.other

import scala.util.control.Breaks

/**
 * 只用一个for循环打印等腰三角形1--3--5--7---(2*n+1)
 *
 * @author karlieswift 
 *         date: 2020/6/23 17:10 
 *         ClassName: Tang  
 * @version java "13.0.1"
 */
object Tang {
  def main(args: Array[String]): Unit = {

    fun1(6)

    /**
     * 方法一
     * @param n
     */
    def fun(n:Int): Unit = {
        for (j <- Range(1, n + 1); i <- Range(0, (2 * n - 1) / 2 + j + 1)) {
          if ((2 * n - 1) / 2 + j == i) {
//            print(i)
            println();
          }
          else if (i <= (2 * n - 1) / 2 - j) {
            System.out.print(" ");

          } else {
            System.out.print("*");
          }
        }
      }
    }

  /**
   * 方法二
   * @param n
   */
  def fun1(n:Int): Unit ={
    for(i<-Range(0,n)){
      println(" "*(n-i-1)+"*"*(2*i+1))
    }
  }



}

