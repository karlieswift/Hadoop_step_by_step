package com.tay.sparkcore.exercise.service

import com.tay.sparkFramwork.core.TService
import com.tay.sparkFramwork.util.EnvUtils
import com.tay.sparkcore.exercise.bean.{HotCategory, UserVisitAction}
import com.tay.sparkcore.exercise.dao.{HotCategoryDao, HotCategorySessionDao}
import com.tay.sparkcore.exercise.helper.HotCategoryAccumulator
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

import scala.collection.mutable
import scala.collection.mutable.ArrayOps

/**
 * @author karlieswift 
 *         date: 2020/7/17 9:25 
 *         ClassName: HotCategoryService  
 * @version java "13.0.1"
 *
 *      需求三 页面单跳转换率统计
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
class HotCategorySessionService extends TService {
  private val hotCategoryDao = new HotCategorySessionDao


  override def analysis(data: Any)= {


    //获取top10热门品类
    val top10: List[HotCategory] = data.asInstanceOf[List[HotCategory]]

    //获取数据
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

    //1-top10集合不需要将整个对象序列化传递给excutor,所以将top10进行结果转换，减少序列化后的字数
    val ids: List[String] = top10.map(hc=>hc.categoryid)


    //2-将top10数据结构转换后，集合存在大量数据，使用广播变量让数据在excutor中共享，而不是冗余存在
    val bc: Broadcast[List[String]] = EnvUtils.getEnv().broadcast(ids)
    //过滤 保留top10热门类数据
    val filterRdd: RDD[UserVisitAction] = actionRdd.filter(action => {
      val click_category_id: Long = action.click_category_id
      var flag = false
      bc.value.foreach(
        id => {
          if (id == click_category_id.toString) {
            flag = true
          }
        }
      )
      flag
    })
    //将过滤的数据进行处理
    val mapRdd: RDD[((Long, String), Int)] = filterRdd.map(
      action => {
        ((action.click_category_id, action.session_id), 1)
      }
    )
    val reduceRdd: RDD[((Long, String), Int)] = mapRdd.reduceByKey(_+_)
    //  action => ((categoryid,session) ,1)=>((categoryid,session) ,sum)
    //将统计的数据结果进行结果变化
    // ((categoryid,session) ,sum)(categoryid,(session,sum))
    val value: RDD[(Long, (String, Int))] = reduceRdd.map {
      case ((categoryid, session), sum) => {
        (categoryid, (session, sum))
      }
    }




    //将装换的数据按商品类别进行分组
    val groupRdd: RDD[(Long, Iterable[(String, Int)])] = value.groupByKey()


    //将同一个组的数据按照点击的数据量进行排序（降序），top10
    val result: RDD[(Long, List[(String, Int)])] = groupRdd.mapValues(
      iter => {
        iter.toList.sortWith(
          (left, right) => {
            left._2 > right._2
          }
        ).take(10)
      }
    )
    result.collect



        }


}