package com.tay.collectionMethod

/**
 * @author karlieswift 
 *         date: 2020/7/2 16:17 
 *         ClassName: CollectionMethod3  
 * @version java "13.0.1"
 *          1-交集，并集，差集
 *
 */
object CollectionMethod3 {
  def main(args: Array[String]): Unit = {
    val ls1 = List(1,2,3,4)
    val ls2 = List(3,4,5,6)

    //并集
    println(ls1.union(ls2)) //List(1, 2, 3, 4, 3, 4, 5, 6)
    //交集
    println(ls1.intersect(ls2)) //List(3, 4)
    //差集
    println(ls1.diff(ls2)) //List(1, 2)

  }

}
