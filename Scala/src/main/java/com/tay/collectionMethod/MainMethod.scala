package com.tay.collectionMethod

import java.util.Random


/**
 * @author karlieswift 
 *         date: 2020/7/2 17:26 
 *         ClassName: MainMethod
 * @version java "13.0.1"
 *
 *          1-map
 *          2-flatMap
 *          3-filter
 *          4-groupBy
 *          5-sortBy
 */
object MainMethod {

  def main(args: Array[String]): Unit = {
    /**
     * 集合映射
     */
//    val ls = List(1,2,3,4,5,6)
//    println(ls.map(x => x * 2)) //对元素整体操作  List(2, 4, 6, 8, 10, 12)
//    println(ls.map(_* 2)) //对元素整体操作 List(2, 4, 6, 8, 10, 12)
//    println(ls.map(_%2==1)) //List(true, false, true, false, true, false)


    /**
     * 集合扁平化
     */

//    val list = List(List(1,2),List(3,4))
//    println(list.mkString(" ")) //List(1, 2) List(3, 4)
//    println(list.flatten)//List(1, 2, 3, 4)
//    val list1 = List("1","2","3","4")

//    val list1 = List("11","2","3","4")
//    println(list1.flatMap(p=>p))//List(1, 1, 2, 3, 4)
//    var list2=List("Hbase","Hive")
//    println(list2.flatMap(p=>p*2))//List(H, b, a, s, e, H, b, a, s, e, H, i, v, e, H, i, v, e)
//    var list2=List("hello Hbase","hello Hive")
//    println(list2.flatMap(_.split(" ")))//List(hello, Hbase, hello, Hive)

    /**
     * filter 过滤
     */
//    var list3=List.range(0,11)
//    println(list3.filter(p=>p%2==1))//List(1, 3, 5, 7, 9)
//    println(list3.filter(_%2==1))//List(1, 3, 5, 7, 9)

    /**
     * 分组 groupBy
     */
//    var list4=List(1,2,3,4,5,6)
//    println(list4.groupBy(_%2)) //Map(1 -> List(1, 3, 5), 0 -> List(2, 4, 6))
//    var list5=List("Hbase","Hive","java")
//    println(list5.groupBy(str=>str.contains("a")))


    /**
     * 排序sortby
     */
    val list6 = List(1,3,2,7,66,11)
    println(list6.sortBy(x=>x)) //升序  List(1, 2, 3, 7, 11, 66)
    println(list6.sortBy(x=> -x)) //降序  List(66, 11, 7, 3, 2, 1)













  }
}
