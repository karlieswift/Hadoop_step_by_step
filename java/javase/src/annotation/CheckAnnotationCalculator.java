package annotation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class CheckAnnotationCalculator {

	public static void main(String[] args) throws IOException {
		//创建对象
		AnnotationCalculator annotationCalculator=new AnnotationCalculator();
		
	
		BufferedWriter bw=null;
		try {
			bw = new BufferedWriter(new FileWriter(new File("test//bug.txt")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n=1;
		
		//获取字节码问价
		//Class clazz=annotationCalculator.getClass();
		Class clazz=annotationCalculator.getClass();
		//获得所有方法
		Method []method=clazz.getMethods();
		for(Method m:method) {
			if(m.isAnnotationPresent(check.class)) {
				try {
					m.invoke(annotationCalculator);
				} catch (Exception e) {
					bw.write("第"+(n++)+"次出现异常 ----"+m.getName());
					bw.write(e.getCause().getMessage());
					bw.newLine();
				} 
			}
		}
		bw.flush();
		bw.close();
		
	}
	
}
