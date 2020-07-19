package com.tay.sparkcore.exercise.service

import com.tay.sparkFramwork.core.TService
import com.tay.sparkFramwork.util.EnvUtils
import com.tay.sparkcore.exercise.bean.{HotCategory, UserVisitAction}
import com.tay.sparkcore.exercise.dao.{PageJumpDao, HotCategorySessionDao}
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

/**
 * @author karlieswift 
 *         date: 2020/7/17 9:25 
 *         ClassName: PageJumpService
 * @version java "13.0.1"
 *
 *          analysis() 指定页面跳转
 *          analysis1() 所有页面跳转
 *
 *          页面[3-9]转换率:0.0196078431372549
 *          页面[7-8]转换率:0.015514809590973202
 *          需求三 页面单跳转换率统计
 *          需求说明
 *          1）页面单跳转化率
 *          计算页面单跳转化率，什么是页面单跳转换率，比如一个用户在一次 Session 过程中访问的页面路径 3,5,7,9,10,21，
 *          那么页面 3 跳到页面 5 叫一次单跳，7-9 也叫一次单跳，那么单跳转化率就是要统计页面点击的概率。
 *          比如：计算 3-5 的单跳转化率，先获取符合条件的 Session 对于页面 3 的访问次数（PV）为 A，
 *          然后获取符合条件的 Session 中访问了页面 3 又紧接着访问了页面 5 的次数为 B，那么 B/A 就是 3-5 的页面单跳转化率。
 *
 *          2）统计页面单跳转化率意义
 *          产品经理和运营总监，可以根据这个指标，去尝试分析，整个网站，产品，各个页面的表现怎么样，是不是需要去优化产品的布局；
 *          吸引用户最终可以进入最后的支付页面。
 *          数据分析师，可以此数据做更深一步的计算和分析。
 *          企业管理层，可以看到整个公司的网站，各个页面的之间的跳转的表现如何，可以适当调整公司的经营战略或策略。
 */
class PageJumpService extends TService {
  private val hotCategoryDao = new PageJumpDao

    override def analysis()= {
    //数据获取

    val rdd: RDD[String] = hotCategoryDao.fromFile("file/user_visit_action.txt")

    val actionRdd: RDD[UserVisitAction] = rdd.map(
      action => {
        val datas: Array[String] = action.split("_")
        UserVisitAction(
          datas(0),
          datas(1).toLong,
          datas(2),
          datas(3).toLong,
          datas(4),
          datas(5),
          datas(6).toLong,
          datas(7).toLong,
          datas(8),
          datas(9),
          datas(10),
          datas(11),
          datas(12).toLong
        )
      }
    )

    actionRdd.cache()

    val list = List(1,2,3,4,5,6,7,8,9,10)
    val tlist: List[String] = list.zip(list.tail).map {
      case (x, y) => (x + "-" + y)
    }

    //计算分母
    val v1: RDD[(Long, Int)] = actionRdd.filter(
      action=> list.contains(action.page_id)
    ).map(
      action => {
        (action.page_id, 1)
      }
    ).reduceByKey(_ + _)
    val toMap: Map[Long, Int] = v1.collect().toMap
    val v2: Broadcast[Map[Long, Int]] = EnvUtils.getEnv().broadcast(toMap)


    //计算分子
    //将数据按session_id分组
    val sessionRdd: RDD[(String, Iterable[UserVisitAction])] = actionRdd.groupBy(_.session_id)


    //必须排序完成后才能过滤-否则结果偏大
    val mapValueRdd: RDD[(String, List[(String, Int)])] = sessionRdd.mapValues(
      iter => {
        //排序：对分组后的数据--时间--组内排序（升序）
        val actions: List[UserVisitAction] = iter.toList.sortWith(
          (left, right) => {
            left.action_time > right.action_time
          }
        )
        //得到结果actions数据有太多没用的信息 需要去掉，只留下page_id 需要将数据结构装换结构
        val pageids: List[Long] = actions.map(_.page_id)

        //[ID1,(1,2,3,4)]
        //数据组合[1,2,3,4]===>[1-2,2-3,3-4]
        // [1,2,3,4].zip([2,3,4])====[1-2,2-3,3-4]
        val zipRdd: List[(Long, Long)] = pageids.zip(pageids.tail)
        //[1-2,2-3,3-4]===>[(1-2,1),(2-3,1),(3-4,1)]
        zipRdd.map {
          case (id1, id2) => {
            (id1 + "-" + id2, 1)
          }
        }.filter(
          ls=>{
            tlist.contains(ls._1)
          }
        )
      }

    )//mapValues end

    //对数据(ID1,[(1-2,1),(2-3,1),(3-4,1)])进行改变
    val mapRdd: RDD[List[(String, Int)]] = mapValueRdd.map(_._2) //[(1-2,1),(2-3,1),(3-4,1)]

    //[(1-2,1),(2-3,1),(3-4,1)]一个数据，拆成(1-2,1),(2-3,1),(3-4,1) 三个数据
    val flatRdd: RDD[(String, Int)] = mapRdd.flatMap(list=>list)

    val pagesumRDD: RDD[(String, Int)] = flatRdd.reduceByKey(_+_)

    //统计页面单调转换率


    pagesumRDD.foreach{
      case(page,sum)=>{
        val pageid: String = page.split("-")(0)
        val total: Int = v2.value.getOrElse(pageid.toLong,1)  //这里一定不会取到1

        println(s"页面[${page}]转换率:"+sum.toDouble/total.toDouble)
      }
    }



  }



  /**
   *
   * ==========================================================================================================
   */
    def analysis1()= {
    //数据获取

    val rdd: RDD[String] = hotCategoryDao.fromFile("file/user_visit_action.txt")

    val actionRdd: RDD[UserVisitAction] = rdd.map(
      action => {
        val datas: Array[String] = action.split("_")
        UserVisitAction(
          datas(0),
          datas(1).toLong,
          datas(2),
          datas(3).toLong,
          datas(4),
          datas(5),
          datas(6).toLong,
          datas(7).toLong,
          datas(8),
          datas(9),
          datas(10),
          datas(11),
          datas(12).toLong
        )
      }
    )

    actionRdd.cache()

    //计算分母
    val v1: RDD[(Long, Int)] = actionRdd.map(
      action => {
        (action.page_id, 1)
      }
    ).reduceByKey(_ + _)
    val toMap: Map[Long, Int] = v1.collect().toMap
    val v2: Broadcast[Map[Long, Int]] = EnvUtils.getEnv().broadcast(toMap)


    //计算分子
    //将数据按session_id分组
    val sessionRdd: RDD[(String, Iterable[UserVisitAction])] = actionRdd.groupBy(_.session_id)


    val mapValueRdd: RDD[(String, List[(String, Int)])] = sessionRdd.mapValues(
      iter => {
        //排序：对分组后的数据--时间--组内排序（升序）
        val actions: List[UserVisitAction] = iter.toList.sortWith(
          (left, right) => {
            left.action_time > right.action_time
          }
        )
        //得到结果actions数据有太多没用的信息 需要去掉，只留下page_id 需要将数据结构装换结构
        val pageids: List[Long] = actions.map(_.page_id)

        //[ID1,(1,2,3,4)]
        //数据组合[1,2,3,4]===>[1-2,2-3,3-4]
        // [1,2,3,4].zip([2,3,4])====[1-2,2-3,3-4]
        val zipRdd: List[(Long, Long)] = pageids.zip(pageids.tail)
        //[1-2,2-3,3-4]===>[(1-2,1),(2-3,1),(3-4,1)]
        zipRdd.map {
          case (id1, id2) => {
            (id1 + "-" + id2, 1)
          }
        }
      }

    )//mapValues end

    //对数据(ID1,[(1-2,1),(2-3,1),(3-4,1)])进行改变
    val mapRdd: RDD[List[(String, Int)]] = mapValueRdd.map(_._2) //[(1-2,1),(2-3,1),(3-4,1)]

    //[(1-2,1),(2-3,1),(3-4,1)]一个数据，拆成(1-2,1),(2-3,1),(3-4,1) 三个数据
    val flatRdd: RDD[(String, Int)] = mapRdd.flatMap(list=>list)

    val pagesumRDD: RDD[(String, Int)] = flatRdd.reduceByKey(_+_)

    //统计页面单调转换率


    pagesumRDD.foreach{
      case(page,sum)=>{
        val pageid: String = page.split("-")(0)
        val total: Int = v2.value.getOrElse(pageid.toLong,1)  //这里一定不会取到1

        println(s"页面[${page}]转换率:"+sum.toDouble/total.toDouble)
      }
    }



  }


}