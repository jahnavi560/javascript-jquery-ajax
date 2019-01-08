package org.coenraets.cellar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionHelper{
	
	/*public static Connection getConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			return (Connection)DriverManager.getConnection("jdbc:mysql://localhost/cellar?user=root&password=root"); 
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection){
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	private String url;
	private static ConnectionHelper instance;

	private ConnectionHelper()
	{
    	String driver = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost/directory?user=root";
            ResourceBundle bundle = ResourceBundle.getBundle("cellar");
            driver = bundle.getString("jdbc.driver");
            Class.forName(driver);
            url=bundle.getString("jdbc.url");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelper();
		}
		try {
			return DriverManager.getConnection(instance.url);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}