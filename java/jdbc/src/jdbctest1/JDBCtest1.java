package jdbctest1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCtest1 {

	public static void main(String[] args) throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
		String username = "root";
		String password = "666666";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		String sql = "UPDATE users SET NAME='fffddff' WHERE PASSWORD='123456'";

		Statement stmt = conn.createStatement();
		int count = stmt.executeUpdate(sql);

		System.out.println(count);

		stmt.close();
		conn.close();
	}
}
