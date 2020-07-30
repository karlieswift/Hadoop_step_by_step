package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 * Collection List------ArrayList
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月7日
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
//         //System.out.println(array[1]==12);//出错
//         System.out.println(array[1].equals("12"));//false
//         List list2=Arrays.asList("a","b","c");
//         //注意：Arrays.asList(…) 方法返回的 List 集合既不是 ArrayList 实例，
//         //也不是 Vector 实例。 Arrays.asList(…)  返回值是一个固定长度的 List 集合
//         //  list2.add("d");//java.lang.UnsupportedOperationException
//		listshow(list2);

//       ArrayList<String> list=new ArrayList<String>();
//        //add返回的是boolean
//       boolean istrue=list.add("karlie1");
//       list.add("karlie2");
//       list.add("karlie3");
//      // list.add("karlie4");
//       list.add("karlie5");
//       listshow(list);
//       //插入位置
//       list.add(3, "karlie4");
//       list.add("karlie3");
//       listshow(list);
//       System.out.println(list.lastIndexOf("karlie3"));
//       //查找某个值的Index
//       System.out.println(list.indexOf("karlie3"));
//       //包含数据
//       System.out.println(list.contains("karlie3"));
//       //移除元素返回boolean,移除的是第一个该值元素
//       System.out.println(list.remove("karlie5"));
//       listshow(list);
//       ///设置index的值,返回被改的元素
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
//       //删除元素元素的删除：在迭代时删除，一定要使用Iterator迭代器对象删除
//       Iterator it=list.iterator();
//       while(it.hasNext()) {
//    	   ///如果进行迭代时用调用此方法之外的其他方式修改了该迭代器所指向的 collection，
//    	   //则迭代器的行为是不确定的。 
//    	  it.next();
//    	  it.remove();
//    	//如果尚未调用 next 方法，或者在上一次调用 next 方法之后已经调用了 remove 方法，报IllegalStateException。
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
		// 遍历的2种方法
//	       for( String str:list) {
//	    	   //不能在遍历的时候进行数组操作
//	    	 //  list.add("12");
//	    	   System.out.println(str);
//	       }
		Iterator it = list.iterator();
		while (it.hasNext()) {
			// 不能在遍历的时候进行数组操作
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
