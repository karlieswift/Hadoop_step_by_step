package com.tay.sparkcore.wordcountFramework
import com.tay.sparkFramwork.core._
import com.tay.sparkFramwork.util.EnvUtils
/**
 * @author karlieswift 
 *         date: 2020/7/10 18:00 
 *         ClassName: WordcountContoller  
 * @version java "13.0.1"
 */
class WordcountContoller extends TController {
  private val service = new WordcountService
  override def dispatcher(): Unit = {

    service.analysis()

  }
}
