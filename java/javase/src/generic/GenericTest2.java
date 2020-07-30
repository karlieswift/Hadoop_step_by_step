package generic;

import java.util.ArrayList;
import java.util.List;

public class GenericTest2 {
	public static void main(String[] args) {
		List<?> list = null;
		list = new ArrayList<String>();
		list = new ArrayList<Double>();
		// list.add(3.0);
		list.add(null);

		List<String> l1 = new ArrayList<String>();
		List<Integer> l2 = new ArrayList<Integer>();
		l1.add("ÉÐ¹è¹È");
		l2.add(15);
		read(l1);
		read(l2);
	}

	static void read(List<?> list) {
		for (Object o : list) {
			System.out.println(o);

		}
	}
}
