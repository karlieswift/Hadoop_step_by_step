package jdbctest2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mysqltest {
	public static void main(String[] args) {
	
		List<Student> list=findall();
//		Iterator<Student> it=list.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		for(Student s:list) {
			System.out.println(s);
		}
	}

	public static List<Student> findall() {

		List<Student> list=new ArrayList<Student>() ;
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
		String username = "root";
		String password = "666666";
		Connection conn = null;
		Statement stmt = null;
		ResultSet res=null;
		try {
			
			Class.forName(driver);
			conn=DriverManager.getConnection(url,username,password);
			String sql="select *from users ";
			stmt=conn.createStatement();
			res=stmt.executeQuery(sql);
			int id;
			String name;
			String pw;
			while(res.next()) {
				id=res.getInt("id");
				name=res.getString("name");
				pw=res.getString("password");
				list.add(new Student(id, name, pw));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	return list;
	}
}
