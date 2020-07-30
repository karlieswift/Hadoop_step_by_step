package commit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CommitTest {
	public static void main(String[] args) {
				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/db3?useSSL=false&serverTimezone=Asia/Shanghai";
				String username = "root";
				String password = "666666";
				Connection conn = null;
				PreparedStatement pst1=null;
				PreparedStatement pst2=null;
				try {
					
					Class.forName(driver);
					conn=DriverManager.getConnection(url,username,password);
		
					
					
					String sql1 = "UPDATE account SET balance=balance-? WHERE id=?";
					String sql2 = "UPDATE account SET balance=balance+? WHERE id=?";
					
					pst1 = conn.prepareStatement(sql1);
					pst1.setInt(1, 500);
					pst1.setInt(2, 1);
					 
				
					pst2 = conn.prepareStatement(sql2);
					pst2.setInt(1, 500);
					pst2.setInt(2, 2);
					pst1.executeUpdate();
			
					pst2.executeUpdate();
	
		
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
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
