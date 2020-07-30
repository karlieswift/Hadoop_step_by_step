package reflection;

public class RefelectionTest {
	public static void main(String[] args) throws Exception {
		 Class clazz1=Class.forName("reflection.Person");
		 System.out.println(clazz1);
		 Class clazz2=Person.class;
		 System.out.println(clazz2);
		 Class clazz3=new Person().getClass();
		 System.out.println(clazz3);
		 System.out.println(clazz1==clazz2);
		 System.out.println(clazz2==clazz3);
		 System.out.println(clazz1==clazz3);
	}

}
