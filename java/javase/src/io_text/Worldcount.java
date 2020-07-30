package io_text;

 
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
/**
 * 
 * @Description: 统计字符数量,并保存文件
 * @author  karlieswift
 * @date 2020年4月6日
 * @version "13.0.1"
 */
public class Worldcount {
	public static void main(String[] args)     {
		String path="test//taylor.txt";
		worldCount(path);
	}
	public static void worldCount(String path) {
		Map<Character, Integer>map = new HashMap<Character, Integer>();;
		String str = "";
		BufferedReader br = null;
		try {
			br=new BufferedReader(new FileReader(new File(path)));
			String data;
			str = "";
			while((data=br.readLine())!=null) {
				str=str+data;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(br!=null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		char []ch=str.toCharArray();
		for(int i=0;i<str.length();i++) {
			if(!map.containsKey(ch[i])) {
				map.put(ch[i], 1);
			}
			else {
				map.put(ch[i],map.get(ch[i])+1);
			}
		}
		Set set=map.keySet();
		Iterator it=set.iterator();
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(new File("test//worldcount.txt")));
			while(it.hasNext()) {
				char ch1=(char) it.next();
				System.out.println(ch1+" "+map.get(ch1));
				bw.write(ch1+" "+map.get(ch1)+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			 
				try {
					if(bw!=null) {
						bw.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		}
	
	}
}
