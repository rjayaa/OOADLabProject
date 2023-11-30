package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.Singleton;
import Model.User;

public class UserController {

	private static Connection connection = Singleton.getInstance().getConnection();

	public void insertUser(User user) {
		String query = "INSERT INTO user (userID, userRole,userName,userEmail,userPassword) VALUES (?,?,?,?,?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserRole());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer inputValidation(String username, String email, String password,String confirmPassword) {
		
		if(username.isEmpty()) return 1;
		else if(!isEmailUnique(email) ) return 2;
		else if(email.isEmpty()) return 3;
		else if(password.length() < 6 || password.isEmpty()) return 4;
		else if(!password.equalsIgnoreCase(confirmPassword) || confirmPassword.isEmpty()) return 5;
		
		return 6;
		
		
	}

	private boolean isEmailUnique(String email){
		String query = "SELECT userEmail FROM user WHERE userEmail = ? ";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) return false;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
		
	}
}
