package com.tay.collectionMethod

/**
 * @author karlieswift 
 *         date: 2020/7/2 16:20 
 *         ClassName: CollectionMethod4  
 * @version java "13.0.1"
 *
 *
 *          1-拉链
 *          2-滑动
 */
object CollectionMethod4 {

  def main(args: Array[String]): Unit = {
    val ls1 = List(1, 2, 3, 4, 5)
    val ls2 = List(3, 4, 5, 6)
    val ls3 = List(3, 4, 5, 6)

    /**
     * zip拉链
     */
    println(ls1.zip(ls2))//List((1,3), (2,4), (3,5), (4,6))
    println(ls2.zip(ls1))//List((3,1), (4,2), (5,3), (6,4))
    val tuples = ls2.zip(ls1)
    println(tuples.zip(ls1)) //List(((3,1),1), ((4,2),2), ((5,3),3), ((6,4),4))

    println(tuples(0)._1) //3



        /**
         * 滑动
         */
        val iterator1 = ls1.sliding(3)
        while (iterator1.hasNext) {
          println(iterator1.next())
        }

        /**
         * List(1, 2, 3)
         * List(2, 3, 4)
         * List(3, 4, 5)
         */

        println("----------------------------------------")
        val iterator2 = ls1.sliding(3,2)
        while (iterator2.hasNext) {
          println(iterator2.next())
        }

        /**
         * List(1, 2, 3)
         * List(3, 4, 5)
         */


  }
}
