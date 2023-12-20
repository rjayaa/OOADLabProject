package controller;

import model.User;
import util.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    private User user;

    public boolean login(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Email must be filled.");
            alert.setHeaderText(null);
            alert.show();
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Password must be filled.");
            alert.setHeaderText(null);
            alert.show();
            return false;
        }

        String query = "SELECT userEmail FROM user WHERE userEmail = ? AND userPassword = ?";
        try {
            Connection connection = Singleton.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                Alert alert = new Alert(AlertType.ERROR, "Invalid email or password.");
                alert.setHeaderText(null);
                alert.show();
                return false;
            }
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR, "An error occurred while trying to log in.");
            alert.setHeaderText(null);
            alert.show();
            e.printStackTrace();
            return false;
        }
    }

    public String getRoleByEmail(String email) {
        String role = null;
        Connection connection = Singleton.getInstance().getConnection();
        try {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT userRole FROM user WHERE userEmail = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("userRole");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return role;
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

}