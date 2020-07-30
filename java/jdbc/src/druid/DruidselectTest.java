package druid;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DruidselectTest {

    public static void main(String[] args) {

    	List<Student> list=new ArrayList<Student>() ;
    	list=findall();
    	for( Student s:list) {
    		System.out.println(s);
    	}
    	

}
    public static List<Student> findall() {
    	List<Student> list=new ArrayList<Student>() ;
    	Connection conn=null;
    	PreparedStatement preparedStatement=null;
    	ResultSet res=null;
    	try {
			conn=JDBCUtils.getConnection();
			String sql="select *from users";
			preparedStatement=conn.prepareStatement(sql);
			res=preparedStatement.executeQuery();
			int id;
			String name;
			String pw;
			while(res.next()) {
				id=res.getInt("id");
				name=res.getString("name");
				pw=res.getString("password");
				list.add(new Student(id, name, pw));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(res,preparedStatement, conn);
			
		}
    	return list;
    	
    }
}

class Student {
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

