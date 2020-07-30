package collection;

import java.util.HashSet;
import java.util.Set;

public class GenericTest {
	public static void main(String[] args) {

		Set<String> set = new HashSet<String>();
		// System.out.println(set.add(125));//出错

		set.add(new String("public"));
		set.add("class");
		set.add("abcdf");
		set.add(new String("abcdfghi"));

		System.out.println("Set集合中的元素个数：" + set.size());
		System.out.println(set);

		for (String s : set) {
			System.out.println("Set集合中的元素：" + s);
			System.out.println(s.toUpperCase());
		}
	}
}
