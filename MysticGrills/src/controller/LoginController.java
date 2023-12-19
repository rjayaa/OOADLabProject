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
    private static Connection connection = Singleton.getInstance().getConnection();

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
        String query = "SELECT userRole FROM user WHERE userEmail = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("userRole");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}