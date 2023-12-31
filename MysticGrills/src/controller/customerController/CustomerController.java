package controller.customerController;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.Receipt;
import util.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class CustomerController {
    private static Connection connection = Singleton.getInstance().getConnection();

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

    // Order Controller
    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> order = new ArrayList<>();
        String query = "SELECT * FROM `order`";

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

    public ArrayList<Order> getAllOrderedOrders() {
        ArrayList<Order> order = new ArrayList<>();
        String query = "SELECT * FROM `order` WHERE orderStatus = 'Served'";

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

    public int getLastId() {
        int lastId = 1;
        String query = "SELECT orderId FROM orderItem ORDER BY orderId DESC LIMIT 1";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                lastId = rs.getInt("orderId");
        } catch (SQLException e) {
        }
        return lastId;
    }

    public int getLastIdOrder() {
        int lastId = 1;
        String query = "SELECT orderId FROM `order` ORDER BY orderId DESC LIMIT 1";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                lastId = rs.getInt("orderId");
        } catch (SQLException e) {
        }
        return lastId;
    }

    public int getMenuItemIdByName(String menuItemName) {
        int menuItemId = 0;
        String query = "SELECT menuItemId FROM menuItem WHERE menuItemName = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, menuItemName);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                menuItemId = rs.getInt("menuItemId");
        } catch (SQLException e) {
        }
        return menuItemId;
    }

    public String getMenuItemById(int id) {
        String menuItemName = "";
        String query = "SELECT menuItemName FROM menuItem WHERE menuItemId = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                menuItemName = rs.getString("menuItemName");
        } catch (SQLException e) {
        }
        return menuItemName;
    }

    public void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }

    public void insertOrderItem(ArrayList<OrderItem> orderItems) {
        String query = "INSERT INTO orderItem (orderId,menuItemId,quantity) VALUES (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            for (OrderItem orderItem : orderItems) {
                ps.setInt(1, orderItem.getOrderId());
                ps.setInt(2, orderItem.getMenuItemId());
                ps.setInt(3, orderItem.getQuantity());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertOrder(Order order) {
        String query = "INSERT INTO `order` (orderId, orderUser, orderItems, orderStatus, orderDate, orderTotal) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, order.getOrderId());
            ps.setInt(2, order.getOrderUser());
            String orderItems = String.join(",", order.getOrderItems());
            ps.setString(3, orderItems);
            ps.setString(4, order.getOrderStatus());
            ps.setTimestamp(5, order.getOrderDate());
            ps.setInt(6, order.getOrderTotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Receipt Controller
    public void insertReceipt(Receipt receipt) {
        String query = "INSERT INTO receipt (receiptOrder, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, receipt.getReceiptOrderId());
            ps.setInt(2, receipt.getReceiptPaymentAmount());
            ps.setTimestamp(3, receipt.getReceiptPaymentDate());
            ps.setString(4, receipt.getReceiptPaymentType());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPriceByOrderId(int orderId) {
        String query = "SELECT SUM(o.quantity * m.menuItemPrice) AS TotalPrice FROM `orderitem` AS o JOIN `menuitem` AS m ON o.menuItemId=m.menuItemId WHERE o.orderId = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("TotalPrice");
            }

        } catch (Exception e) {

        }
        return 0;
    }

    public ArrayList<Receipt> getAllReceiptsByUserId(int userId) {
        ArrayList<Receipt> receipts = new ArrayList<>();
        String query = "SELECT * FROM receipt INNER JOIN `order` ON receipt.receiptOrder = `order`.orderId WHERE `order`.orderUser = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int receiptId = resultSet.getInt("receiptId");
                int receiptOrderId = resultSet.getInt("receiptOrder");
                int receiptPaymentAmount = resultSet.getInt("receiptPaymentAmount");
                Timestamp receiptPaymentDate = resultSet.getTimestamp("receiptPaymentDate");
                String receiptPaymentType = resultSet.getString("receiptPaymentType");

                Receipt receipt = new Receipt(receiptId, receiptOrderId, receiptPaymentAmount, receiptPaymentDate,
                        receiptPaymentType);
                receipts.add(receipt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return receipts;
    }

}
