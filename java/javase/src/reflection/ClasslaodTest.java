package reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClasslaodTest {
	public static void main(String[] args) throws IOException {
		//�����Զ����࣬ʹ��ϵͳ����������м���
        ClassLoader classLoader = ClasslaodTest.class.getClassLoader();
        System.out.println(classLoader);
      //����ϵͳ���������getParent()����ȡ��չ�������
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
      //������չ���������getParent()���޷���ȡ�����������
        //�������������Ҫ�������java�ĺ�����⣬�޷������Զ�����ġ�
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);
        
        
        
        
     
	}

}
