package com.tay.sparkcore.exercise.application
import com.tay.sparkFramwork._
import com.tay.sparkFramwork.core.Tapplication
import com.tay.sparkcore.exercise.controller.HotCategoryController
/**
 * @author karlieswift 
 *         date: 2020/7/17 9:14 
 *         ClassName: HotCategoryAnalysisApplication
 * @version java "13.0.1"
 */
object HotCategoryAnalysisApplication extends  App with Tapplication{

  /**
   * 热门品类排行top10
   */

  excute{
    val controller = new HotCategoryController
    controller.dispatcher()
  }
  /**
   * output:
   * (15,(6120,1672,1259))
   * (2,(6119,1767,1196))
   * (20,(6098,1776,1244))
   * (12,(6095,1740,1218))
   * (11,(6093,1781,1202))
   * (17,(6079,1752,1231))
   * (7,(6074,1796,1252))
   * (9,(6045,1736,1230))
   * (19,(6044,1722,1158))
   * (13,(6036,1781,1161))
   */
}
