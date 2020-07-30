package reflection;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class RefectionDemo {
	public static void main(String[] args) throws Exception {
		// ����������󣬿���ִ�з���
//		Person p=new Person();
//		p.personfun();

		// ͨ��������� ���������� ͨ�������ļ�
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
