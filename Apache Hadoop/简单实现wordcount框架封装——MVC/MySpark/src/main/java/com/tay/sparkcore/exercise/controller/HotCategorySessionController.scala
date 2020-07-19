package com.tay.sparkcore.exercise.controller

import com.tay.sparkFramwork.core.TController
import com.tay.sparkcore.exercise.bean.HotCategory
import com.tay.sparkcore.exercise.service.{HotCategoryService, HotCategorySessionService}

/**
 * @author karlieswift 
 *         date: 2020/7/17 9:21 
 *         ClassName: HotCategorySessionController
 * @version java "13.0.1"
 */
class HotCategorySessionController extends TController{

  private val hotCategoryService = new HotCategoryService
  private val hotCategorySessionService = new HotCategorySessionService

  /**
   * 调度
   */
  override def dispatcher(): Unit = {

    //top10热门类品
    val categorys: List[HotCategory] = hotCategoryService.analysis()
    val value= hotCategorySessionService.analysis(categorys)

    value.foreach(println)



  }


}
