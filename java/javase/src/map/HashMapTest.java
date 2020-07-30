package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
 

/**
 * HashMap���ϲ���
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020��4��8��
 * @version "13.0.1"
 * 
 * 
 *          put(key,value);//����key��Ӧ��vaule
 *          ���е�key��һ��set���ϣ����е�vaule��һ��collection����
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

	// 1-���е�key��һ��set���ϣ�
	public static void showmap1(Map map) {
		Set sets = map.keySet();
		for (Object key : sets) {
			System.out.println(key + ":" + map.get(key));
		}
	}

	// 2-���е�vaule��һ��collection����
	public static void showmap2(Map map) {
		Collection coll = map.values();
		for (Object vaule : coll) {
			System.out.println(vaule);
		}
	}

	// 3-���е�ӳ����һ��set����
	public static void showmap3(Map map) {
		Set sets = map.entrySet();
		for (Object obj : sets) {
			Map.Entry m = (Map.Entry) obj;
			System.out.println(m.getKey() + ":" + m.getValue());
		}
	}

}
