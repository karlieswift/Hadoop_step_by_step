package StudentSort;

public class Student implements Comparable<Student> {

	private String name;
	private int age;
	MyDate bitrhday;
	
	
	public Student(String name, int age, MyDate bitrhday) {
		super();
		this.name = name;
		this.age = age;
		this.bitrhday = bitrhday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((bitrhday == null) ? 0 : bitrhday.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (bitrhday == null) {
			if (other.bitrhday != null)
				return false;
		} else if (!bitrhday.equals(other.bitrhday))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public MyDate getBitrhday() {
		return bitrhday;
	}
	public void setBitrhday(MyDate bitrhday) {
		this.bitrhday = bitrhday;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", bitrhday=" + bitrhday + "]";
	}
	@Override
	public int compareTo(Student o) {
	
		int name=this.name.compareTo(o.name);
		int age=this.age-o.age;
		if(name!=0) {
			return name;
		}else if(age!=0){
			return age;
		}else {
			return this.bitrhday.compareTo(o.bitrhday);
		}
	}
	
}
