package com.tay.mymatch

/**
 * @author karlieswift 
 *         date: 2020/7/5 13:39 
 *         ClassName: Match4  
 * @version java "13.0.1"
 *          模式匹配练习
 */
object Match4 {
  def main(args: Array[String]): Unit = {
    val Array(x,y,_*) = Array(1,2,3,4,5,6)
    println(s"$x"+":"+s"$y")

    val Person1(x1,y1) = Person1("taylor",121)
    println(s"$x1"+":"+s"$y1")


    val map = Map("A" -> 1, "B" -> 0, "C" -> 3)

    println(
      map.map(kv=>{
        (kv._1,kv._2+1)
      }).toList
    )


    println(
      map.map{
        case (k,v)=>{
          (k,v+1)
        }
      }.toList
    )

  }
}
