package reflection;

import java.lang.reflect.Constructor;

public class ClassLoaderTset1 {
	public static void main(String[] args) throws Exception {
		//1.根据全类名获取对应的Class对象
		String name = "reflection";
		Class clazz = null;
		clazz = Class.forName(name);
		//2.调用指定参数结构的构造器，生成Constructor的实例
		Constructor con = clazz.getConstructor(String.class,Integer.class);
		//3.通过Constructor的实例创建对应类的对象，并初始化类属性
		Person p2 = (Person)con.newInstance("Peter",20);
		System.out.println(p2);

	}

}
