package reflection;

import java.lang.reflect.Constructor;

public class ClassLoaderTset1 {
	public static void main(String[] args) throws Exception {
		//1.����ȫ������ȡ��Ӧ��Class����
		String name = "reflection";
		Class clazz = null;
		clazz = Class.forName(name);
		//2.����ָ�������ṹ�Ĺ�����������Constructor��ʵ��
		Constructor con = clazz.getConstructor(String.class,Integer.class);
		//3.ͨ��Constructor��ʵ��������Ӧ��Ķ��󣬲���ʼ��������
		Person p2 = (Person)con.newInstance("Peter",20);
		System.out.println(p2);

	}

}
