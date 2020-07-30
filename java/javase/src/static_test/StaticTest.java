package static_test;

public class StaticTest {
	public static void main(String[] args) {

		Student student_1 = new Student("taylor");
		Student student_2 = new Student("karlie");
		System.out.println(student_1.getName()+" "+student_1.getId());
		System.out.println(student_2.getName()+" "+student_2.getId());
		System.out.println(Student.getCounts());
	}
}

class Student {
	private int id;
	private String name;
	private static int init = 1001;
	private static int counts;

	public Student(String name) {
		id = id + (init++);
		counts++;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static int getCounts() {
		return counts;
	}

}
