package jdbcTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTestConnection {
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException{
		return MySQLConnUtils.getMySQLConnection();
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		System.out.println("Get connection...");
		
		Connection conn = MySQLTestConnection.getMyConnection();
		
		System.out.println("Opened connection:" + conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "SELECT * FROM `wp_users`;";
	
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			int userID = rs.getInt(1);
			String userLogin = rs.getString(2);
			String userEmail = rs.getString("user_email");
			
			System.out.println("----------------------");
			System.out.println("User Id:" + userID);
			System.out.println("User login:" + userLogin);
			System.out.println("User email:" + userEmail);
		}
		
		conn.close();
		System.out.println("-----------Closed connection-----------");
	}
}
