package com.tay.sparkcore.exercise.service

import com.tay.sparkFramwork.core.TService
import com.tay.sparkFramwork.util.EnvUtils
import com.tay.sparkcore.exercise.bean.HotCategory
import com.tay.sparkcore.exercise.dao.HotCategoryDao
import com.tay.sparkcore.exercise.helper.HotCategoryAccumulator
import org.apache.spark.rdd.RDD

import scala.collection.immutable.StringOps
import scala.collection.mutable
import scala.collection.mutable.ArrayOps

/**
 * @author karlieswift 
 *         date: 2020/7/17 9:25 
 *         ClassName: HotCategoryService  
 * @version java "13.0.1"
 *          analysis1()与analysis2() 是一种分析的思路，不是最终代码
 *          analysis3()：在 analysis1()与analysis2() 基础上优化代码
 *          analysis() :用累加器进行实现
 *
 *
 *
 *
 *
 *          需求一：统计热门品类前10
 *
 *
 *
 *
 *
 *          品类是指产品的分类，大型电商网站品类分多级，咱们的项目中品类只有一级，不同的公司可能对热门的定义不一样。
 *          我们按照每个品类的点击、下单、支付的量来统计热门品类。
 *          鞋			点击数 下单数  支付数
 *          衣服		点击数 下单数  支付数
 *          电脑		点击数 下单数  支付数
 *
 *          本项目需求优化为：先按照点击数排名，靠前的就排名高；如果点击数相同，再比较下单数；下单数再相同，就比较支付数。
 *
 *
 *          部分数据：
 *          2019-07-17_95_26070e87-1ad7-49a3-8fb3-cc741facaddf_37_2019-07-17 00:00:02_手机_-1_-1_null_null_null_null_3
 *          2019-07-17_95_26070e87-1ad7-49a3-8fb3-cc741facaddf_48_2019-07-17 00:00:10_null_16_98_null_null_null_null_19
 *          2019-07-17_95_26070e87-1ad7-49a3-8fb3-cc741facaddf_6_2019-07-17 00:00:17_null_19_85_null_null_null_null_7
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_29_2019-07-17 00:00:19_null_12_36_null_null_null_null_5
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_22_2019-07-17 00:00:28_null_-1_-1_null_null_15,1,20,6,4_15,88,75_9
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_11_2019-07-17 00:00:29_苹果_-1_-1_null_null_null_null_7
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_24_2019-07-17 00:00:38_null_-1_-1_15,13,5,11,8_99,2_null_null_10
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_24_2019-07-17 00:00:48_null_19_44_null_null_null_null_4
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_47_2019-07-17 00:00:54_null_14_79_null_null_null_null_2
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_27_2019-07-17 00:00:59_null_3_50_null_null_null_null_26
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_27_2019-07-17 00:01:05_i7_-1_-1_null_null_null_null_17
 *          2019-07-17_38_6502cdc9-cf95-4b08-8854-f03a25baa917_24_2019-07-17 00:01:07_null_5_39_null_null_null_null_10
 *
 *          数据介绍：
 *          上面的数据图是从数据文件中截取的一部分内容，表示为电商网站的用户行为数据，主要包含用户的4种行为：搜索，点击，下单，支付。数据规则如下：
 *          	数据文件中每行数据采用下划线分隔数据
 *          	每一行数据表示用户的一次行为，这个行为只能是4种行为的一种
 *          	如果搜索关键字为null,表示数据不是搜索数据
 *          	如果点击的品类ID和产品ID为-1，表示数据不是点击数据
 *          	针对于下单行为，一次可以下单多个商品，所以品类ID和产品ID可以是多个，id之间采用逗号分隔，如果本次不是下单行为，则数据采用null表示
 *          	支付行为和下单行为类似
 *          详细字段说明：
 *          编号	字段名称	字段类型	字段含义
 *          1	date	String	用户点击行为的日期
 *          2	user_id	Long	用户的ID
 *          3	session_id	String	Session的ID
 *          4	page_id	Long	某个页面的ID
 *          5	action_time	String	动作的时间点
 *          6	search_keyword	String	用户搜索的关键词
 *          7	click_category_id	Long	某一个商品品类的ID
 *          8	click_product_id	Long	某一个商品的ID
 *          9	order_category_ids	String	一次订单中所有品类的ID集合
 *          10	order_product_ids	String	一次订单中所有商品的ID集合
 *          11	pay_category_ids	String	一次支付中所有品类的ID集合
 *          12	pay_product_ids	String	一次支付中所有商品的ID集合
 *          13	city_id	Long	城市 id
 *
 *
 *
 *          样例类：
 *          //用户访问动作表
 *          case class UserVisitAction(
 *          date: String,//用户点击行为的日期
 *          user_id: Long,//用户的ID
 *          session_id: String,//Session的ID
 *          page_id: Long,//某个页面的ID
 *          action_time: String,//动作的时间点
 *          search_keyword: String,//用户搜索的关键词
 *          click_category_id: Long,//某一个商品品类的ID
 *          click_product_id: Long,//某一个商品的ID
 *          order_category_ids: String,//一次订单中所有品类的ID集合
 *          order_product_ids: String,//一次订单中所有商品的ID集合
 *          pay_category_ids: String,//一次支付中所有品类的ID集合
 *          pay_product_ids: String,//一次支付中所有商品的ID集合
 *          city_id: Long
 *          )//城市 id
 *
 */
class HotCategoryService extends TService {
  private val hotCategoryDao = new HotCategoryDao

  /**
   * 累加器实现
   */
     override def analysis() = {
    // 读取数据
    val actionRdd: RDD[String] = hotCategoryDao.fromFile("file/user_visit_action.txt")


    /**
     *  创建累加器，向spark注册
     *  在原来的基础上创建包  helper
     */

    val accumulator = new HotCategoryAccumulator
    EnvUtils.getEnv().register(accumulator,"xx")



    // 遍历数据，将数据向累加器传递
    actionRdd.foreach(
      action=>{
        val datas: Array[String] = action.split("_")
        if(datas(6)!="-1"){//点击数据
          accumulator.add(datas(6),"click")
        }
        else  if(datas(8)!="null"){//订单数据
          val ids: Array[String] = datas(8).split(",")
          ids.foreach(
            id=>{
              accumulator.add(id,"order")
            }
          )
        }
        else  if(datas(10)!="null"){//支付数据
          val ids: Array[String] = datas(10).split(",")
          ids.foreach(
            id=>{
              accumulator.add(id,"pay")
            }
          )
        }
      }
    )


    //获取累加器值，进行排序-降序 top10

    val value: mutable.Map[String, HotCategory] = accumulator.value

    val categories: mutable.Iterable[HotCategory] = value.map(_._2)
    categories.toList.sortWith(
      (left,right)=>{
        var i1=left.clickCount-right.clickCount
        var i2=left.orderCount-right.orderCount
        if(i1!=0){
          i1>0
        }else if(i2!=0){
          i2>0
        }
        else {
          left.payCount>right.payCount
        }
      }
    ).take(10)
  }





  /**
   * 下面是优化的代码，
   *    1-主要对合并时的join进行优化
   *  改变复杂度 O(n*n*n) -->O(3*n)
   * 思路：将join相乘级别 改成 union+reduceByKey 的相加级别
   *
   *   2-因为-点击-下单-支付都用到reduceByKey 进行多次shuffle
   *   优化：减少shuffle的次数
   * 2019-07-17_95_26070e87-1ad7-49a3-8fb3-cc741facaddf_37_2019-07-17 00:00:02_手机_-1_-1_null_null_null_null_3
   * 2019-07-17_95_26070e87-1ad7-49a3-8fb3-cc741facaddf_48_2019-07-17 00:00:10_null_16_98_null_null_null_null_19
   * 分析
   */
   def analysis3() = {

    /**
     * 1 从用户行为数据文件读取数据
     */
    //从HotCategoryDao 获取
    val actionRdd: RDD[String] = hotCategoryDao.fromFile("file/user_visit_action.txt")

    val value: RDD[(String, (Int, Int, Int))] = actionRdd.flatMap {
      lines => {
        val datas: Array[String] = lines.split("_")
        if (datas(6) != "-1") { //点击数据
          List((datas(6), (1, 0, 0)))
        }
        else if (datas(8) != "null") { //订单数据
          val ids: Array[String] = datas(8).split(",")
          ids.map((_, (0, 1, 0)))
        }
        else if (datas(10) != "null") { //支付数据
          val ids: Array[String] = datas(10).split(",")
          ids.map((_, (0, 0, 1)))
        }
        else {
          Nil
        }
      }
    }
    val tRDD: RDD[(String, (Int, Int, Int))] = value.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )
    /**
     *结果排序 降序排名 top10
     * 元组数据可以直接多层次排序
     */

    val result: Array[(String, (Int, Int, Int))] = tRDD.sortBy(_._2, false).take(10)
    result

  }


  /**
   * ===============================================================================================================
   */


  /**
   * 下面是优化的代码，主要对   第5步 join进行优化
   *  改变复杂度 O(n*n*n) -->O(3*n)
   * 思路：将join相乘级别 改成 union+reduceByKey 的相加级别
   * 分析
   */
 def analysis2() = {

    /**
     * 1 从用户行为数据文件读取数据
     */
    //从HotCategoryDao 获取
    val actionRdd: RDD[String] = hotCategoryDao.fromFile("file/user_visit_action.txt")
    //actionRdd下面会用到三次重复使用，可以优化，放到cache
    actionRdd.cache()

    /**
     * 2 点击统计
     */
    //切分数据 click_category_id为第七个
    val click_category_id: RDD[(String, Int)] = actionRdd.map(
      lines => {
        val datas: Array[String] = lines.split("_")
        (datas(6), 1)
      }
    )
    val acction_click_count: RDD[(String, Int)] = click_category_id.filter(_._1 != "-1") //进行数据过滤 如果点击的品类ID和产品ID为-1，表示数据不是点击数据
      .reduceByKey(_ + _) //对类别进行相加

    /**
     * 3 下单统计
     */
    //order_category_ids	String	一次订单中所有品类的ID集合
    val order_category_ids: RDD[(String, Int)] = actionRdd.map(
      lines => {
        val datas: Array[String] = lines.split("_")
        (datas(8), 1)
      }
    )
    //因为下单可以多个，需要拆分
    val actionOrderRdd: RDD[(String, Int)] = order_category_ids.filter(_._1 != null)
    val actoionOrdersRdd: RDD[(String, Int)] = actionOrderRdd.flatMap {
      case (orderId, one) => {
        val orderIds: ArrayOps.ofRef[String] = orderId.split(",")
        orderIds.map((_, one))
      }
    }
    val actoion_order_count: RDD[(String, Int)] = actoionOrdersRdd.reduceByKey(_ + _)


    /**
     * 4 支付统计
     */
    val order_pay_ids: RDD[(String, Int)] = actionRdd.map(
      lines => {
        val datas: Array[String] = lines.split("_")
        (datas(10), 1)
      }
    )
    //因为下单可以多个，需要拆分
    val actionPayRdd: RDD[(String, Int)] = order_pay_ids.filter(_._1 != null)
    val actoionPaysRdd: RDD[(String, Int)] = actionPayRdd.flatMap {
      case (payId, one) => {
        val payIds: ArrayOps.ofRef[String] = payId.split(",")
        payIds.map((_, one))
      }
    }
    val actoion_pay_count: RDD[(String, Int)] = actoionPaysRdd.reduceByKey(_ + _)


    /**
     * 5 合并：将点击-下单-支付连在一起
     * 点击：(A,12)->A(12,0,0)
     * 下单：(A,32)->A(0,32,0)
     * 支付：(A,122)->A(0,0,122)
     * 通过union得到
     * (A(12,0,0) A(0,32,0) A(0,0,122))
     *  通过reduceByKey得到
     * (A,(12,32,122))
     *
     */
    val new_acction_click_count: RDD[(String, (Int, Int, Int))] = acction_click_count.map {
      case (id, clickcount) => {
        (id, (clickcount, 0, 0))
      }
    }
    val new_acction_order_count: RDD[(String, (Int, Int, Int))] = actoion_order_count.map {
      case (id, ordercount) => {
        (id, (0,ordercount, 0))
      }
    }
    val new_actoion_pay_count: RDD[(String, (Int, Int, Int))] = actoion_pay_count.map {
      case (id, paycount) => {
        (id, (0,0,paycount))
      }
    }

    val sumRDD: RDD[(String, (Int, Int, Int))] = new_acction_click_count.union(new_acction_order_count).union(new_actoion_pay_count)

    val tRDD: RDD[(String, (Int, Int, Int))] = sumRDD.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )

    /**
     * 6 结果排序 降序排名 top10
     * 元组数据可以直接多层次排序
     */

    val result: Array[(String, (Int, Int, Int))] = tRDD.sortBy(_._2, false).take(10)
    result

  }


  /**
   * ===============================================================================================================
   */

  /**
   * 下面是优化前的代码，主要对   第5步 join进行优化
   * 分析
   */
    def analysis1() = {

    /**
     * 1 从用户行为数据文件读取数据
     */
    //从HotCategoryDao 获取
    val actionRdd: RDD[String] = hotCategoryDao.fromFile("file/user_visit_action.txt")
    //actionRdd下面会用到三次重复使用，可以优化，放到cache
    actionRdd.cache()






    /**
     * 2 点击统计
     */
    //切分数据 click_category_id为第七个
    val click_category_id: RDD[(String, Int)] = actionRdd.map(
      lines => {
        val datas: Array[String] = lines.split("_")
        (datas(6), 1)
      }
    )
    val acction_click_count: RDD[(String, Int)] = click_category_id.filter(_._1 != "-1") //进行数据过滤 如果点击的品类ID和产品ID为-1，表示数据不是点击数据
      .reduceByKey(_ + _) //对类别进行相加

    /**
     * 3 下单统计
     */
    //order_category_ids	String	一次订单中所有品类的ID集合
    val order_category_ids: RDD[(String, Int)] = actionRdd.map(
      lines => {
        val datas: Array[String] = lines.split("_")
        (datas(8), 1)
      }
    )
    //因为下单可以多个，需要拆分
    val actionOrderRdd: RDD[(String, Int)] = order_category_ids.filter(_._1 != null)
    val actoionOrdersRdd: RDD[(String, Int)] = actionOrderRdd.flatMap {
      case (orderId, one) => {
        val orderIds: ArrayOps.ofRef[String] = orderId.split(",")
        orderIds.map((_, one))
      }
    }
    val actoion_order_count: RDD[(String, Int)] = actoionOrdersRdd.reduceByKey(_ + _)


    /**
     * 4 支付统计
     */
    val order_pay_ids: RDD[(String, Int)] = actionRdd.map(
      lines => {
        val datas: Array[String] = lines.split("_")
        (datas(10), 1)
      }
    )
    //因为下单可以多个，需要拆分
    val actionPayRdd: RDD[(String, Int)] = order_pay_ids.filter(_._1 != null)
    val actoionPaysRdd: RDD[(String, Int)] = actionPayRdd.flatMap {
      case (payId, one) => {
        val payIds: ArrayOps.ofRef[String] = payId.split(",")
        payIds.map((_, one))
      }
    }
    val actoion_pay_count: RDD[(String, Int)] = actoionPaysRdd.reduceByKey(_ + _)


    /**
     * 5 合并：将点击-下单-支付连在一起
     */
    val sumRdd: RDD[(String, ((Int, Int), Int))] = acction_click_count.join(actoion_order_count).join(actoion_pay_count)
    //改变结构(String, ((Int, Int), Int))==>(String, (Int, Int, Int))
    val tRDD: RDD[(String, (Int, Int, Int))] = sumRdd.mapValues {
      case ((order, click), pay) => {
        (order, click, pay)
      }
    }


    /**
     * 6 结果排序 降序排名 top10
     * 元组数据可以直接多层次排序
     */

    val result: Array[(String, (Int, Int, Int))] = tRDD.sortBy(_._2, false).take(10)
    result

  }


}
