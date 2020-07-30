package reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClasslaodTest {
	public static void main(String[] args) throws IOException {
		//对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClasslaodTest.class.getClassLoader();
        System.out.println(classLoader);
      //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
      //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);
        
        
        
        
     
	}

}
