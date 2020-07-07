package com.tay.collectionMethod

/**
 * @author karlieswift 
 *         date: 2020/7/2 16:35 
 *         ClassName: CaculatorFun1  
 * @version java "13.0.1"
 *
 *          1- min,max,sum,produce
 *          2- reduce reduceRight reduceLeft
 *          3- fold foldRight foldLeft   fold就是在reduce的基础上加了一个基数
 *          4- scan scanRight scanLeft
 */
object CaculatorFun1 {

  def main(args: Array[String]): Unit = {

    val ls1 = List.range(1,7)
//    println(ls1) //List(1, 2, 3, 4, 5, 6)

//    println(ls1.max)
//    println(ls1.min)
//    println(ls1.sum)
//    println((ls1.sum).toDouble/ls1.size) //3.5
//
//    println(ls1.product) //720     所有元素乘积
    //    println((3.1/6).formatted("%.2f")) //0.52

    /**
     * 逐个操作
     */
    //    val ls2 = List(1,2,3,4)
//    println(ls2.reduce(_-_)) //   1-2-3-4=-8
//    println(ls2.reduceRight(_-_)) // 1-(2-(3-4))=-2
//    println(ls2.reduceLeft(_-_)) //   1-2-3-4=-8

    /**
     *
     *
     * fold就是在reduce的基础上加了一个基数
     */
//        val ls2 = List(1,2,3,4)
//        println(ls2.fold(2)(_-_)) // 2-1-2-3-4=-8
//        println(ls2.fold(3)(_*_)) // 3*1*2*3*4=72
//
//        println(ls2.foldRight(11)(_-_)) // 1-(2-(3-(11-4))=9
//        println(ls2.foldLeft(11)(_-_)) // 11-1-2-3-4=1


    /**
     * scan 扫描 就是记录fold的过程
     */
            val ls2 = List(1,2,3,4)
            println(ls2.scan(2)(_-_)) //List(2, 1, -1, -4, -8)
            println(ls2.scanRight(11)(_-_))  //List(9, -8, 10, -7, 11)
            println(ls2.scanLeft(11)(_-_))   //List(11, 10, 8, 5, 1)


  }
}
