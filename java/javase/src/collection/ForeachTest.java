package collection;

/**
 * Foreach增强for循环
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020年4月7日
 * @version "13.0.1"
 */
public class ForeachTest {
	public static void main(String[] args) {
		String[] str = new String[2];
		for (String myStr : str) {
			myStr = "karliekloss";
			System.out.println(myStr);
		}
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
	}

}
