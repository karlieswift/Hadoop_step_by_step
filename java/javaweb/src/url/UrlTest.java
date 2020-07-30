package url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *  URL������
 * 1.URL:ͳһ��Դ��λ������Ӧ�Ż�������ĳһ��Դ��ַ
 * 2.��ʽ��
 *  http://localhost:8080/examples/beauty.jpg?username=Tom
 *  Э��   ������    �˿ں�  ��Դ��ַ           �����б�
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��12��
 * @version "13.0.1"
 */
public class UrlTest {
	public static void main(String[] args) throws IOException {

		URL url = new URL("http://localhost:8080/test/1.jpg?username=Tom");
		//URL url = new URL("test//12.jpg");
//      public String getProtocol(  )     ��ȡ��URL��Э����
      System.out.println(url.getProtocol());//http
//      public String getHost(  )           ��ȡ��URL��������
      System.out.println(url.getHost());//localhost
//      public String getPort(  )            ��ȡ��URL�Ķ˿ں�
      System.out.println(url.getPort());//8080
//      public String getPath(  )           ��ȡ��URL���ļ�·��
      System.out.println(url.getPath());///test/1.jpg
//      public String getFile(  )             ��ȡ��URL���ļ���
      System.out.println(url.getFile());///test/1.jpg?username=Tom
//      public String getQuery(   )        ��ȡ��URL�Ĳ�ѯ��
      System.out.println(url.getQuery());//username=Tom
	}

}
