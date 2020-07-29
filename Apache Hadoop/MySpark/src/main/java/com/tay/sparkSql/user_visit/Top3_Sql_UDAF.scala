package com.tay.sparkSql.user_visit

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{Encoder, Encoders, SparkSession, functions}

import scala.collection.mutable

/**
 * @author karlieswift 
 *         date: 2020/7/21 10:19 
 *         ClassName: Top3_Sql_UDAF  
 * @version java "13.0.1"
 *          需求：各区域热门商品 Top3
 * 3.2.1 需求简介
 *          这里的热门商品是从点击量的维度来看的，计算各个区域前三大热门商品，并备注上每个商品在主要城市中的分布比例，超过两个城市用其他显示。
 *          例如：
 *          地区	商品名称	点击次数	城市备注
 *          华北	商品A	100000	北京21.2%，天津13.2%，其他65.6%
 *          华北	商品P	80200	北京63.0%，太原10%，其他27.0%
 *          华北	商品M	40000	北京63.0%，太原10%，其他27.0%
 *          东北	商品J	92000	大连28%，辽宁17.0%，其他 55.0%
 * 3.2.2 需求分析
 *          	查询出来所有的点击记录，并与 city_info 表连接，得到每个城市所在的地区，与 Product_info 表连接得到产品名称
 *          	按照地区和商品 id 分组，统计出每个商品在每个地区的总点击次数
 *          	每个地区内按照点击次数降序排列
 *          	只取前三名
 *          	城市备注需要自定义 UDAF 函数
 * 3.2.3 功能实现
 *          	连接三张表的数据，获取完整的数据（只有点击）
 *          	将数据根据地区，商品名称分组
 *          	统计商品点击次数总和,取Top3
 *          	实现自定义聚合函数显示备注
 */
object Top3_Sql_UDAF {
  def main(args: Array[String]): Unit = {
    //todo 设置属性
    System.setProperty("HADOOP_USER_NAME", "tay")

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    //todo 连接Hive
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()

    spark.sql("use tay")

    //todo top3

    spark.sql(
      """
        |select u.*,c.area,c.city_name,p.product_name
        |from user_visit_action u
        |join city_info  c on c.city_id=u.city_id
        |join product_info p on u.click_product_id=p.product_id
        |where u.click_product_id > 0
        |""".stripMargin
    ).createOrReplaceTempView("t1")



    spark.udf.register("cityRemark", functions.udaf(new CityRemarkUDAF))

    spark.sql(
      """
        |select t1.area,t1.product_name, count(*) as clickcount,
        |cityRemark(t1.city_name)  as citypercentcitypercentcitypercent
        |from t1 group by t1.area,t1.product_name
        |""".stripMargin
    ).createOrReplaceTempView("t2")

    spark.sql(
      """
        |select *,
        |rank() over(partition by t2.area order by t2.clickcount desc) ranks
        |from t2
        |""".stripMargin
    ).createOrReplaceTempView("t3")

    spark.sql(
      """
        |select *
        |from t3 where t3.ranks<4
        |""".stripMargin).show()

    /**
     *
     * +----+------------+----------+---------------------------------+-----+
     * |area|product_name|clickcount|citypercentcitypercentcitypercent|ranks|
     * +----+------------+----------+---------------------------------+-----+
     * |华东|     商品_86|       371|       上海:16%,杭州:15%,其他:69%|    1|
     * |华东|     商品_47|       366|       杭州:15%,青岛:15%,其他:70%|    2|
     * |华东|     商品_75|       366|       上海:17%,无锡:15%,其他:68%|    2|
     * |西北|     商品_15|       116|                西安:54%,银川:45%|    1|
     * |西北|      商品_2|       114|                银川:53%,西安:46%|    2|
     * |西北|     商品_22|       113|                西安:54%,银川:45%|    3|
     * |华南|     商品_23|       224|       厦门:29%,福州:24%,其他:47%|    1|
     * |华南|     商品_65|       222|       深圳:27%,厦门:26%,其他:47%|    2|
     * |华南|     商品_50|       212|       福州:27%,深圳:25%,其他:48%|    3|
     * |华北|     商品_42|       264|       郑州:25%,保定:25%,其他:50%|    1|
     * |华北|     商品_99|       264|       北京:24%,郑州:23%,其他:53%|    1|
     * |华北|     商品_19|       260|       郑州:23%,保定:20%,其他:57%|    3|
     * |东北|     商品_41|       169|      哈尔滨:35%,大连:34%,其他...|    1|
     * |东北|     商品_91|       165|      哈尔滨:35%,大连:32%,其他...|    2|
     * |东北|     商品_58|       159|       沈阳:37%,大连:32%,其他:31%|    3|
     * |东北|     商品_93|       159|      哈尔滨:38%,大连:37%,其他...|    3|
     * |华中|     商品_62|       117|                武汉:51%,长沙:48%|    1|
     * |华中|      商品_4|       113|                长沙:53%,武汉:46%|    2|
     * |华中|     商品_57|       111|                武汉:54%,长沙:45%|    3|
     * |华中|     商品_29|       111|                武汉:50%,长沙:49%|    3|
     * +----+------------+----------+---------------------------------+-----+
     */


    spark.stop()
  }


  /**
   * 自定义函数：实现city 备注
   * 定义泛型
   * In:string  city
   * buff: 1-CityMap[K,v]===>k=city  v==>clickcount  ,2-totalcount
   * out:城市备注：string
   *
   */
  case class Buff(var total: Long, var cityMap: mutable.Map[String, Long])

  class CityRemarkUDAF extends Aggregator[String, Buff, String] {
    //初始化
    override def zero: Buff = Buff(0L, mutable.Map[String, Long]())

    //计算分区之间
    override def reduce(b: Buff, a: String): Buff = {
      b.total += 1
      val map: mutable.Map[String, Long] = b.cityMap
      val i: Long = map.getOrElse(a, 0L)
      map.update(a, i + 1)
      b
    }

    override def merge(b1: Buff, b2: Buff): Buff = {
      b1.total += b2.total
      val map1: mutable.Map[String, Long] = b1.cityMap
      val map2: mutable.Map[String, Long] = b2.cityMap
      b1.cityMap = map1.foldLeft(map2) {
        case (map, (k, v)) => {
          val i: Long = map.getOrElse(k, 0)
          map.update(k, i + v)
          map
        }
      }
      b1
    }

    override def finish(reduction: Buff): String = {
      val mapcitys: mutable.Map[String, Long] = reduction.cityMap
      val total: Long = reduction.total
      val cityclicksort: List[(String, Long)] = mapcitys.toList.sortWith(
        (left, right) => {
          left._2 > right._2
        }
      ).take(2)
      val citynumber=mapcitys.size

      var sum=0L
      val builder = new StringBuilder
      cityclicksort.foreach{
        case(city,count)=>{
          val l: Long = count*100 /total

          builder.append(city+":"+l+"% ")
          sum =sum+ l

        }
      }
      if(citynumber>2) {
        builder.append("其他:"+(100-sum)+"%")
      }
      builder.toString().split(" ").mkString(",")
    }

    override def bufferEncoder: Encoder[Buff] =Encoders.product

    override def outputEncoder: Encoder[String] = Encoders.STRING
  }

}
