package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 * Collection List------ArrayList
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��7��
 * @version "13.0.1"
 */
public class ListTest {

	public static void main(String[] args) {

//		 ArrayList list=new ArrayList();
//         list.add("taylor");  list.add(12);
//         Object[] array=list.toArray();
//         System.out.println(array[0].getClass());//class java.lang.String
//         System.out.println(array[1].getClass());//class java.lang.Integer
//         int a=12; Integer b=12;System.out.println(a==b);//true
//         //System.out.println(array[1]==12);//����
//         System.out.println(array[1].equals("12"));//false
//         List list2=Arrays.asList("a","b","c");
//         //ע�⣺Arrays.asList(��) �������ص� List ���ϼȲ��� ArrayList ʵ����
//         //Ҳ���� Vector ʵ���� Arrays.asList(��)  ����ֵ��һ���̶����ȵ� List ����
//         //  list2.add("d");//java.lang.UnsupportedOperationException
//		listshow(list2);

//       ArrayList<String> list=new ArrayList<String>();
//        //add���ص���boolean
//       boolean istrue=list.add("karlie1");
//       list.add("karlie2");
//       list.add("karlie3");
//      // list.add("karlie4");
//       list.add("karlie5");
//       listshow(list);
//       //����λ��
//       list.add(3, "karlie4");
//       list.add("karlie3");
//       listshow(list);
//       System.out.println(list.lastIndexOf("karlie3"));
//       //����ĳ��ֵ��Index
//       System.out.println(list.indexOf("karlie3"));
//       //��������
//       System.out.println(list.contains("karlie3"));
//       //�Ƴ�Ԫ�ط���boolean,�Ƴ����ǵ�һ����ֵԪ��
//       System.out.println(list.remove("karlie5"));
//       listshow(list);
//       ///����index��ֵ,���ر��ĵ�Ԫ��
//       System.out.println(list.set(1, "taylor"));
//       listshow(list);
//       
//       ArrayList<String> list2=new ArrayList<String>();
//       list2.add("taylor1");
//       list2.add("taylor1");
//       list2.add("taylor1");
//
//       list.addAll(1,list2);
//       listshow(list);
//       //ɾ��Ԫ��Ԫ�ص�ɾ�����ڵ���ʱɾ����һ��Ҫʹ��Iterator����������ɾ��
//       Iterator it=list.iterator();
//       while(it.hasNext()) {
//    	   ///������е���ʱ�õ��ô˷���֮���������ʽ�޸��˸õ�������ָ��� collection��
//    	   //�����������Ϊ�ǲ�ȷ���ġ� 
//    	  it.next();
//    	  it.remove();
//    	//�����δ���� next ��������������һ�ε��� next ����֮���Ѿ������� remove ��������IllegalStateException��
//       }
//       
//System.out.println("1222222222222222222222");
//       listshow(list);
//       System.out.println(list.size());
//       
//       

		
		ArrayList list=new ArrayList();
		list.add(new Person(12, "taylor"));
		list.add(new Person(12, "taylor"));
		listshow(list);//Person [age=12, name=taylor] Person [age=12, name=taylor] 
	}

	public static void listshow(List list) {
		// ������2�ַ���
//	       for( String str:list) {
//	    	   //�����ڱ�����ʱ������������
//	    	 //  list.add("12");
//	    	   System.out.println(str);
//	       }
		Iterator it = list.iterator();
		while (it.hasNext()) {
			// �����ڱ�����ʱ������������
			// list.add("12");
			System.out.print(it.next() + " ");
		}
		System.out.println();
	}
}

class Person{
	int age;
	String name;
	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
}
