package controller.cashierController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import model.Order;
import util.Singleton;

public class CashierController {
    private static Connection connection = Singleton.getInstance().getConnection();

    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> order = new ArrayList<>();
        String query = "SELECT * FROM `order` WHERE orderStatus = 'Unpaid'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int orderUser = resultSet.getInt("orderUser");
                String orderItemsString = resultSet.getString("orderItems");
                String orderStatus = resultSet.getString("orderStatus");
                java.sql.Timestamp orderDate = resultSet.getTimestamp("orderDate");
                int orderTotal = resultSet.getInt("orderTotal");

                ArrayList<String> orderItems = new ArrayList<>(Arrays.asList(orderItemsString.split(",")));

                order.add(new Order(orderId, orderUser, orderItems, orderStatus, orderDate, orderTotal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public void updateOrderStatusById(int id) {
        String query = "UPDATE `order` SET orderStatus = ? WHERE orderId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Pending");
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
