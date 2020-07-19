package com.tay.sparkcore.exercise.bean

/**
 * @author karlieswift 
 *         date: 2020/7/17 17:56 
 *         ClassName: HotCategory  
 * @version java "13.0.1"
 */



/**
 * 热门品类
 * @param categoryid
 * @param clickCount
 * @param orderCount
 * @param payCount
 */
case class HotCategory(
                        categoryid:String,
                       var clickCount:Int,
                        var orderCount:Int,
                        var   payCount:Int)








