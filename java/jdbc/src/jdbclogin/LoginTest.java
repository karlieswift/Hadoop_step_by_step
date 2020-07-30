package jdbclogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginTest {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		System.out.println("输入名字：");
		String name=scan.next();
		System.out.println("输入密码:");
		String pdd=scan.next();
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
			//String sql="select *from users where name='"+name+"and password='"+pdd+"'";
            String sql = "select * from users where name = '"+name+"' and password = '"+pdd+"' ";

			stmt=conn.createStatement();
			res=stmt.executeQuery(sql);
			if(res.next()) {
				System.out.println("登入成功");
			}else {
				System.out.println("登录失败");
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
