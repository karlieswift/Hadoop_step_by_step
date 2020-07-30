package jdbclogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Logintest2 {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		System.out.println(" ‰»Î√˚◊÷£∫");
		String name=scan.next();
		System.out.println(" ‰»Î√‹¬Î:");
		String pdd=scan.next();
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
		String username = "root";
		String password = "666666";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet res=null;
		try {
			
			Class.forName(driver);
			conn=DriverManager.getConnection(url,username,password);
			//String sql="select *from users where name='"+name+"and password='"+pdd+"'";
            String sql = "select * from users where name = ? and password =?";

            pst=conn.prepareStatement(sql);
			pst.setString(1,name );
			pst.setString(2,pdd );
			res=pst.executeQuery();
			if(res.next()) {
				System.out.println("µ«»Î≥…π¶");
			}else {
				System.out.println("µ«¬º ß∞‹");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pst != null) {
					pst.close();
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
