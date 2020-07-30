package annotation;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import reflection.RefectionDemo;

//@pro(className = "annotation.Person",methodName = "personfun")
@pro(className = "annotation.Student",methodName = "studentfun")
public class AnnotationTest {

	public static void main(String[] args) throws Exception {
	
		//解析注解
		//获取字节码文件
		Class<AnnotationTest> clazzannotation= AnnotationTest.class;
		//获取注解对象
		pro anPro=clazzannotation.getAnnotation(pro.class);
		String className=anPro.className();
		String methodName=anPro.methodName();
		Class clazz=Class.forName(className);
		Object obj=clazz.getDeclaredConstructor().newInstance();
		Method method=clazz.getMethod(methodName);
		method.invoke(obj);
		
		

	}
}
