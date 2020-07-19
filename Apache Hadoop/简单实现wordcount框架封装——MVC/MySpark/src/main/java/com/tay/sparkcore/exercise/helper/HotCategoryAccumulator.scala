package com.tay.sparkcore.exercise.helper
import scala.collection.mutable
import com.tay.sparkcore.exercise.bean.HotCategory
import org.apache.spark.util.AccumulatorV2

/**
 * @author karlieswift 
 *         date: 2020/7/17 17:54 
 *         ClassName: HotCategoryAccumulator  
 * @version java "13.0.1"
 *          累加器
 */

/**
 *  泛型：
 *  input:(品类id,行为类型)
 *  output:mutable.Map[品类id,HotCategory]
 */
class HotCategoryAccumulator extends AccumulatorV2[(String,String),mutable.Map[String,HotCategory]]{



  var hotCategoryMap=mutable.Map[String,HotCategory]()

  //判断累加器是否初始化
  override def isZero: Boolean = {

    hotCategoryMap.isEmpty
  }

  //复制累加器
  override def copy(): AccumulatorV2[(String, String), mutable.Map[String,HotCategory]] = {
  new HotCategoryAccumulator
}

  //重置累加器
  override def reset(): Unit = {
    hotCategoryMap.clear()
  }

  //累加器添加数据
  override def add(v: (String, String)): Unit = {
    val categoryid: String = v._1
    val actiontype: String = v._2
 val hotCategory: HotCategory = hotCategoryMap.getOrElse(categoryid,HotCategory(categoryid,0,0,0))
  actiontype match {
    case "click" => hotCategory.clickCount+=1
    case "order" =>hotCategory.orderCount+=1
    case "pay" =>hotCategory.payCount+=1
  }
    hotCategoryMap.update(categoryid,hotCategory)

  }

  //不同累加器合并结果
  override def merge(other: AccumulatorV2[(String, String), mutable.Map[String,HotCategory]]): Unit = {
    other.value.foreach{
      case (categoryid,other1)=>{
        val thishotCategory: HotCategory = hotCategoryMap.getOrElse(categoryid,HotCategory(categoryid,0,0,0))

        thishotCategory.clickCount+=other1.clickCount
        thishotCategory.orderCount+=other1.orderCount
        thishotCategory.payCount+=other1.payCount

        hotCategoryMap(categoryid)=thishotCategory
      }
    }
  }

  //返回结果
  override def value: mutable.Map[String,HotCategory] = {
         hotCategoryMap
  }
}
