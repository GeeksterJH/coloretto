package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private static final String URL = "jdbc:mysql://localhost:3306/coloretto";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	private static Connection connection = null;
	
	public static void init() {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.err.println("Failed to connect to database! Exiting...");
			System.exit(-1);
		}
	}
	
	public static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ResultSet query(String sql) {
		if (connection == null) {
			return null;
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}
	
	public static void update(String sql) {
		if (connection == null) {
			return;
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
