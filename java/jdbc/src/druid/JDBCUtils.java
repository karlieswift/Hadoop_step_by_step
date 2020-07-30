package druid;




import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid连接池的工具类
 */
public class JDBCUtils {
	
	//1.定义成员变量 DataSource
	private static DataSource ds;
	static {
		Properties pros=new Properties();
		InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
		 //1.加载配置文件
		try {
			pros.load(is);
			//2.获取DataSource
			ds=DruidDataSourceFactory.createDataSource(pros);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 /**
     * 获取连接
	 * @throws SQLException 
     */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	/**
	 * 释放资源
	 */
	
	public static void close(Statement satStatement,Connection connection) {
		close(null,satStatement, connection);	
	}
	
	
	
	public static void close(ResultSet resultSet,Statement satStatement,Connection connection) {
		
		if(resultSet!=null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(satStatement!=null) {
			try {
				satStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
    /**
     * 获取连接池方法
     */

	public static DataSource getDataSource() {
		return ds;
	}

}
