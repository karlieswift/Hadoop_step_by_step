package class_super;

public class Student extends Person {
	public Student(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}


	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}


	int id;
	
	
	
	public Student(String name, int age, int id) {
		super(name, age);
		this.id = id;
	}


	public void show() {
		super.show();
		System.out.println("student");
	}
	

	


}
