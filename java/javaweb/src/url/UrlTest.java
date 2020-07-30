package url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *  URL网络编程
 * 1.URL:统一资源定位符，对应着互联网的某一资源地址
 * 2.格式：
 *  http://localhost:8080/examples/beauty.jpg?username=Tom
 *  协议   主机名    端口号  资源地址           参数列表
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月12日
 * @version "13.0.1"
 */
public class UrlTest {
	public static void main(String[] args) throws IOException {

		URL url = new URL("http://localhost:8080/test/1.jpg?username=Tom");
		//URL url = new URL("test//12.jpg");
//      public String getProtocol(  )     获取该URL的协议名
      System.out.println(url.getProtocol());//http
//      public String getHost(  )           获取该URL的主机名
      System.out.println(url.getHost());//localhost
//      public String getPort(  )            获取该URL的端口号
      System.out.println(url.getPort());//8080
//      public String getPath(  )           获取该URL的文件路径
      System.out.println(url.getPath());///test/1.jpg
//      public String getFile(  )             获取该URL的文件名
      System.out.println(url.getFile());///test/1.jpg?username=Tom
//      public String getQuery(   )        获取该URL的查询名
      System.out.println(url.getQuery());//username=Tom
	}

}
