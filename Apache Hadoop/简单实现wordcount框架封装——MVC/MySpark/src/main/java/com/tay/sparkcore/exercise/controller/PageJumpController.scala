package com.tay.sparkcore.exercise.controller

import com.tay.sparkFramwork.core.TController
import com.tay.sparkcore.exercise.bean.HotCategory
import com.tay.sparkcore.exercise.service.{PageJumpService, HotCategoryService}

/**
 * @author karlieswift 
 *         date: 2020/7/17 9:21 
 *         ClassName: HotCategoryJumpController
 * @version java "13.0.1"
 */
class PageJumpController extends TController{

  private val service = new PageJumpService

  /**
   * 调度
   */
  override def dispatcher(): Unit = {


    service.analysis()


  }


}
