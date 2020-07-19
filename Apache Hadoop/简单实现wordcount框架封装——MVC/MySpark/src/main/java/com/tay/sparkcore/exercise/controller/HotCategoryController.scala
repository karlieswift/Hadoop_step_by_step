package com.tay.sparkcore.exercise.controller

import com.tay.sparkFramwork.core.{TController, TService}
import com.tay.sparkcore.exercise.bean.HotCategory
import com.tay.sparkcore.exercise.dao.HotCategoryDao
import com.tay.sparkcore.exercise.service.HotCategoryService

/**
 * @author karlieswift 
 *         date: 2020/7/17 9:21 
 *         ClassName: HotCategoryService  
 * @version java "13.0.1"
 */
class HotCategoryController extends TController{

  private val service = new HotCategoryService

  /**
   * 调度
   */
  override def dispatcher(): Unit = {

    val result: List[HotCategory] = service.analysis()


    result.foreach(println)


  }


}
