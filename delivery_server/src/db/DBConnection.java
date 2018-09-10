package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// connection을 만들 때 사용하는 static class

public class DBConnection {
	public static void initConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!!");			
		} catch (ClassNotFoundException e) {			 
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			System.out.println("DB Connection Success!!");
		} catch (SQLException e) {			 
			e.printStackTrace();
		}		
		return conn;
	}

}




