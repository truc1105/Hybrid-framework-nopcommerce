package jdbcTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerJTDSTestConnection {
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException{
		return SQLServerJTDSConnUtils.getSQLServerConnection();
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		System.out.println("Get connection...");
		
		Connection conn = SQLServerJTDSTestConnection.getMyConnection();
		
		System.out.println("Opened connection:" + conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "SELECT * FROM [automationtest].[dbo].[Product_Type];";
	
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {
		
			String empFirstName = rs.getString(1);
			String empLastName = rs.getString("NAME");
			
			System.out.println("----------------------");
			System.out.println("Emp Firstname:" + empFirstName);
			System.out.println("Emp Lastname:" + empLastName);
		}
		
		conn.close();
		System.out.println("-----------Closed connection-----------");
	}
}
