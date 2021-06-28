package Ven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconnecting {
	private static String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "hr";
	private static String pw = "tiger";
	
	public static Connection connecting() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}	
}
