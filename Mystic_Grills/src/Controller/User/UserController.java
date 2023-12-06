package Controller.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBConnection.Singleton;
import Model.User.User;

public class UserController {

	private static Connection connection = Singleton.getInstance().getConnection();

	public ArrayList<User> showUserTable() {
		ArrayList<User> user = new ArrayList<>();
		String query = "SELECT * FROM User";

		try {
			Connection connection = Singleton.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String userRole = resultSet.getString("userRole");
				String userName = resultSet.getString("userName");
				String userEmail = resultSet.getString("userEmail");
				String userPassword = resultSet.getString("userPassword");

				user.add(new User(userId,userRole,userName , userEmail, userPassword));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void insertUser(User user) {
		String query = "INSERT INTO user (userId, userRole,userName,userEmail,userPassword) VALUES (?,?,?,?,?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getUserRole());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getUserEmail());
			ps.setString(5, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer inputValidation(String username, String email, String password, String confirmPassword) {

		if (username.isEmpty())
			return 1;
		else if (!isEmailUnique(email))
			return 2;
		else if (email.isEmpty())
			return 3;
		else if (password.length() < 6 || password.isEmpty())
			return 4;
		else if (!password.equalsIgnoreCase(confirmPassword) || confirmPassword.isEmpty())
			return 5;

		return 6;

	}

	public Integer loginValidation(String email, String password) {
		String query = "SELECT userEmail FROM user WHERE userEmail = ? AND userPassword = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next())
				return 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	private boolean isEmailUnique(String email) {
		String query = "SELECT userEmail FROM user WHERE userEmail = ? ";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next())
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public User getUserByEmail(String email) {
		User user = null;
		String query = "SELECT * FROM user WHERE userEmail = ?";
		try (Connection connection = Singleton.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String userRole = resultSet.getString("userRole");
				String userName = resultSet.getString("userName");
				String userEmail = resultSet.getString("userEmail");
				String userPassword = resultSet.getString("userPassword");

				user = new User(userId, userRole, userName, userEmail, userPassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return user;
	}
	

	
	public void updateUser(String username, String newRole) {
	    try {
	        Connection connection = Singleton.getInstance().getConnection();
	        String query = "UPDATE user SET userRole = ? WHERE userName = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, newRole);
	        ps.setString(2, username);
	        ps.executeUpdate();

	        ps.close();
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public int getLastId() {
		int lastId = 1;
		String query = "SELECT userId FROM user ORDER BY userId DESC LIMIT 1";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				lastId = rs.getInt("userId");
		} catch (SQLException e) {
		}
		return lastId;
	}

}
