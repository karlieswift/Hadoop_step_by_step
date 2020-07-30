package singlenton;

public class SingLenTon {

	public static void main(String[] args) {
		System.out.println("单设计模式饿汉式方法：");
		Sing_1 s_1_1 = Sing_1.getSing_1();
		Sing_1 s_1_2 = Sing_1.getSing_1();
		System.out.println(s_1_1 == s_1_2);
		System.out.println("单设计模式懒汉式方法：");
		Sing_2 s_2_1 = Sing_2.getSing_2();
		Sing_2 s_2_2 = Sing_2.getSing_2();
		System.out.println(s_2_1 == s_2_2);
	}

}

/**
 * 
 * @Description: <饿汉式>
 * @author karlieswift
 * @date 2020年3月28日
 * @version "13.0.1"
 */
class Sing_1 {
	// 私有化构造器
	private Sing_1() {

	}
	// 内部创建类的对象
	private static Sing_1 instance_1 = new Sing_1();

	
	/**
	 *  提供公共方法
	 * @Function @return
	 */
	public static Sing_1 getSing_1() {
		return instance_1;
	}
}

/**
 * 
 * @Description: <懒汉式>
 * @author karlieswift
 * @date 2020年3月28日
 * @version "13.0.1"
 */
class Sing_2 {
	// 私有化构造器
	private Sing_2() {

	}

	// 内部创建类的对象
	private static Sing_2 instance_2 = null;


	/**
	 *  提供公共方法
	 * @Function @return
	 */
	public static Sing_2 getSing_2() {
		if (instance_2 == null) {
			instance_2 = new Sing_2();
		}
		return instance_2;
	}

}
