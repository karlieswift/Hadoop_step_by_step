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
		//��������
		AnnotationCalculator annotationCalculator=new AnnotationCalculator();
		
	
		BufferedWriter bw=null;
		try {
			bw = new BufferedWriter(new FileWriter(new File("test//bug.txt")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n=1;
		
		//��ȡ�ֽ����ʼ�
		//Class clazz=annotationCalculator.getClass();
		Class clazz=annotationCalculator.getClass();
		//������з���
		Method []method=clazz.getMethods();
		for(Method m:method) {
			if(m.isAnnotationPresent(check.class)) {
				try {
					m.invoke(annotationCalculator);
				} catch (Exception e) {
					bw.write("��"+(n++)+"�γ����쳣 ----"+m.getName());
					bw.write(e.getCause().getMessage());
					bw.newLine();
				} 
			}
		}
		bw.flush();
		bw.close();
		
	}
	
}
