package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;
import util.Singleton;

public class RegisterController {
    private static Connection connection = Singleton.getInstance().getConnection();

    public void register(String username, String email, String password, String confirmPassword) {
        Alert alert = new Alert(AlertType.ERROR);

        if (username.isEmpty()) {
            alert.setContentText("Username cannot be empty");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (email.isEmpty()) {
            alert.setContentText("Email cannot be empty");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (!isEmailUnique(email)) {
            alert.setContentText("Email must be unique");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (password.isEmpty()) {
            alert.setContentText("Password cannot be empty");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (password.length() < 6) {
            alert.setContentText("Password must at least be 6 characters long");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (!password.equals(confirmPassword)) {
            alert.setContentText("Passwords do not match");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        int userId = getLastId() + 1;
        User newUser = new User(userId, "Customer", username, email, password);
        insertRegisterUser(newUser);
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

    private boolean isEmailUnique(String email) {
        String query = "SELECT userEmail FROM user WHERE userEmail = ? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void insertRegisterUser(User user) {
        // Insert user into database
        String query = "INSERT INTO user (userId, userRole,userName,userEmail,userPassword) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getUserId());
            ps.setString(2, "Customer");
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getUserEmail());
            ps.setString(5, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}