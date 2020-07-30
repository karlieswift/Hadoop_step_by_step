package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
 

/**
 * HashMap集合测试
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020年4月8日
 * @version "13.0.1"
 * 
 * 
 *          put(key,value);//返回key对应的vaule
 *          所有的key是一个set集合，所有的vaule是一个collection集合
 * 
 */
public class HashMapTest {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap();
		map.put("taylor1", 12);
		map.put("taylor2", 1);
		map.put("taylor2", 12);
		map.put("taylor3", 122);
	 
		showmap1(map);
		System.out.println("+++++++++++++++++++++++++++");
		showmap2(map);
		System.out.println("+++++++++++++++++++++++++++");
		showmap3(map);
		System.out.println("+++++++++++++++++++++++++++");
	}

	// 1-所有的key是一个set集合，
	public static void showmap1(Map map) {
		Set sets = map.keySet();
		for (Object key : sets) {
			System.out.println(key + ":" + map.get(key));
		}
	}

	// 2-所有的vaule是一个collection集合
	public static void showmap2(Map map) {
		Collection coll = map.values();
		for (Object vaule : coll) {
			System.out.println(vaule);
		}
	}

	// 3-所有的映射是一个set集合
	public static void showmap3(Map map) {
		Set sets = map.entrySet();
		for (Object obj : sets) {
			Map.Entry m = (Map.Entry) obj;
			System.out.println(m.getKey() + ":" + m.getValue());
		}
	}

}
