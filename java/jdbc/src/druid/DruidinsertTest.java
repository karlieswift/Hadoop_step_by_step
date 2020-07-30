package druid;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 使用新的工具类
 */
public class DruidinsertTest {

    public static void main(String[] args) {

    	Connection conn=null;
    	PreparedStatement preparedStatement=null;
    	try {
			conn=JDBCUtils.getConnection();
			String sql="insert into users values(null,?,?)";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, "赵倩");
			preparedStatement.setString(2, "123456");
			int count=preparedStatement.executeUpdate();
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(preparedStatement, conn);
			
		}
    	
    	
    }

}
