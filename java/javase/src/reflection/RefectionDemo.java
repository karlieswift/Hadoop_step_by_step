package reflection;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class RefectionDemo {
	public static void main(String[] args) throws Exception {
		// 创建任意对象，可以执行方法
//		Person p=new Person();
//		p.personfun();

		// 通过反射完成 调用任意类 通过配置文件
		Properties pro = new Properties();
		InputStream is = RefectionDemo.class.getClassLoader().getResourceAsStream("pro.properties");
		pro.load(is);
		String className = pro.getProperty("className");
		String methodName = pro.getProperty("methodName");
		Class clazz=Class.forName(className);
		//Object obj=clazz.newInstance();
		Object obj=clazz.getDeclaredConstructor().newInstance();
		Method method=clazz.getMethod(methodName);
		method.invoke(obj);
		

	}

}
