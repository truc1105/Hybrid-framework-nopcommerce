package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerJTDSConnUtils {
	public static Connection getSQLServerConnection() {
		String hostName = "localhost";
		String sqlInstanceName = "SQLExpress";
		String database= "automationtest";
		String userName = "sa";
		String password = "automationfc";
		
		return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);
	}
	
	private static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String userName, String password) {
		Connection conn = null;
		try {
			String connectionURL = "jdbc:jtds:sqlserver://"+ hostName + ":1433/" + database + ";instance=" + sqlInstanceName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
