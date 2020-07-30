package jdbctest2;

public class Student {
	private int id;
	private String name;
	private String pw;
	
	
	public Student(int id, String name, String pw) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", pw=" + pw + "]";
	}
	

}
