package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Model.User.User;

public class Singleton {
	private static Singleton instance = null;
	private Connection connection;
	private User currentUser;
	private Singleton() {
		connectToDatabase();
	}
	
	private void connectToDatabase() {
		try {
			String url = "jdbc:mysql://localhost:3306/mysticgrills";
			String user = "root";
			String password = "";
			this.connection = DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			throw new RuntimeException("Error Connecting to the Database", e);
		}
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection() {
		try {
			if(connection==null || connection.isClosed()) {
				connectToDatabase();
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error checking connection status", e);
		}
		return connection;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
}
