package url;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * HttpURLConnection URL
 * 
 * 从服务器下载图片
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020年4月12日
 * @version "13.0.1"
 */

public class UrlTest2 {

	public static void main(String[] args) throws IOException {

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("test//3.jpg")));// 保存位置

		InputStream is = null;
		HttpURLConnection httpURLConnection = null;
		URL url = new URL(
				"https://img20.360buyimg.com/mobilecms/s280x280_jfs/t1/103754/32/17720/78439/5e8c4e46E91227c21/13f06d3aa1e20861.jpg"
				);

		httpURLConnection = (HttpURLConnection) url.openConnection();
		is = httpURLConnection.getInputStream();
		int len;
		byte[] buffer = new byte[100];
		while ((len = is.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}

		System.out.println("保存完成");

		bos.close();
		is.close();
		httpURLConnection.disconnect();
	}
}
