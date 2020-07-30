package jdbctest1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCselect {

	public static void main(String[] args) {
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
			while(res.next()) {
				id=res.getInt("id");
				name=res.getString("name");
				System.out.println(id+" "+name);
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
	}
}
