package com.tay.objectTest

/**
 * @author karlieswift 
 *         date: 2020/6/29 19:08 
 *         ClassName: User1  
 * @version java "13.0.1"
 */
object User1 {
  def main(args: Array[String]): Unit = {


    new User3().fun()
  }
}

class User2 {


  /**
   *
   * TODO Scala 面向对象 - 属性 - 访问权限
   * 访问权限：方法的提供者和方法的调用者之间的关系
   * Java :
   *          1. 私有的 ： private    => 同类
   *          2. 包     ： (default)  => 同类，同包
   *          3. 受保护的：protected  => 同类，同包，子类
   *          4. 公共的 ： public     => 任意地方
   * Scala :
   *          1. 私有的 ： private       => 同类
   *          2. 包     ： private[包名] => 同类，指定包
   * 这里的包名可以不是固定的，但是需要和当前的包路径有关系
   *          3. 受保护的：protected     => 同类，子类
   *          4. 公共的 ： (default)     => 任意地方
   *
   *
   *
   * public:在不同包下，只要导入，就能用这个变量
   * deafault：在不同包下，只要导入，就能用这个变量
   *      --private[com] var deafault_name: String = ""
   *      这里传入的是"com",也就是说只要在"com"下的包都可以访问
   *      例如下面的例子：User5Child.fun(),在package com.ss.tt下
   *      当可以访问 private[com] var deafault_name，--如果改成com下一级
   *      的目录将不能访问
   *
   *
   * protected：
   * 1-在不同包下，即使导入，也不能用这个protected变量
   * 2-如果不同包下的一个类继承该类，可以用这个protected变量
   * 例如：User5Child是其他包下的一个类
   *            package com.ss.tt
                class User5Child extends com.tay.objectTest.User2{
                    def fun(): Unit = {
                        val user = new com.tay.objectTest.User2()
                        println(deafault_name)
                        println(protected_name)
                        println(public_name)
             }}
   *
   *
   * private：只能本类用
   */
  private var private_name: String = ""
  protected var protected_name: String = ""
  private[com] var deafault_name: String = ""
  var public_name: String = ""


}

class User3 extends User2 {
  def fun(): Unit = {
    val user = new User2()
   println(deafault_name)
   println(protected_name)
   println(public_name)

  }
}