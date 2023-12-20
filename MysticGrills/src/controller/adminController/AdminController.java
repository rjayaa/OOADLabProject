package controller.adminController;

import javafx.stage.Stage;
import model.User;
import model.MenuItem;
import util.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Statement;

public class AdminController {
    private static Connection connection = Singleton.getInstance().getConnection();

    public ArrayList<User> getAllUser() {
        ArrayList<User> user = new ArrayList<>();
        String query = "SELECT * FROM User";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String userRole = resultSet.getString("userRole");
                String userName = resultSet.getString("userName");
                String userEmail = resultSet.getString("userEmail");
                String userPassword = resultSet.getString("userPassword");

                user.add(new User(userId, userRole, userName, userEmail, userPassword));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(int id, String newRole) {
        try {
            String query = "UPDATE user SET userRole = ? WHERE userId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newRole);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            String query = "DELETE FROM User WHERE userId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MenuItem> getAllMenuItem() {
        ArrayList<MenuItem> menuItem = new ArrayList<>();
        String query = "SELECT * FROM menuItem";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int menuItemId = resultSet.getInt("menuItemId");
                String menuItemName = resultSet.getString("menuItemName");
                String menuItemDescription = resultSet.getString("menuItemDescription");
                int menuItemPrice = resultSet.getInt("menuItemPrice");

                menuItem.add(new MenuItem(menuItemId, menuItemName, menuItemDescription, menuItemPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItem;
    }

    public void addMenuItem(String menuItemName, String menuItemDescription, double menuItemPrice) throws Exception {
        if (menuItemName == null || menuItemName.isEmpty()) {
            throw new Exception("Menu Item Name cannot be empty.");
        }

        String checkQuery = "SELECT * FROM menuItem WHERE menuItemName = ?";
        PreparedStatement checkPs = connection.prepareStatement(checkQuery);
        checkPs.setString(1, menuItemName);
        ResultSet rs = checkPs.executeQuery();
        if (rs.next()) {
            throw new Exception("Menu Item Name must be unique.");
        }

        if (menuItemDescription == null || menuItemDescription.length() <= 10) {
            throw new Exception("Menu Item Description must be more than 10 characters.");
        }

        if (menuItemPrice < 2.5) {
            throw new Exception("Menu Item Price must be a number that is greater than or equal to 2.5.");
        }

        try {
            String query = "INSERT INTO menuItem (menuItemName, menuItemDescription, menuItemPrice) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, menuItemName);
            ps.setString(2, menuItemDescription);
            ps.setDouble(3, menuItemPrice);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMenuItem(int id, String newMenuItemName, String newMenuItemDescription, double newMenuItemPrice)
            throws Exception {
        if (newMenuItemName == null || newMenuItemName.isEmpty()) {
            throw new Exception("Menu Item Name cannot be empty.");
        }

        String checkQuery = "SELECT * FROM menuItem WHERE menuItemName = ?";
        PreparedStatement checkPs = connection.prepareStatement(checkQuery);
        checkPs.setString(1, newMenuItemName);
        ResultSet rs = checkPs.executeQuery();
        if (rs.next()) {
            throw new Exception("Menu Item Name must be unique.");
        }

        if (newMenuItemDescription == null || newMenuItemDescription.length() <= 10) {
            throw new Exception("Menu Item Description must be more than 10 characters.");
        }

        if (newMenuItemPrice < 2.5) {
            throw new Exception("Menu Item Price must be a number that is greater than or equal to 2.5.");
        }

        try {
            String query = "UPDATE menuItem SET menuItemName = ?, menuItemDescription = ?, menuItemPrice = ? WHERE menuItemId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newMenuItemName);
            ps.setString(2, newMenuItemDescription);
            ps.setDouble(3, newMenuItemPrice);
            ps.setInt(4, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenuItem(int id) {
        try {
            String query = "DELETE FROM menuItem WHERE menuItemId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
