package Controller.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;



import DBConnection.Singleton;
import Model.MenuItem.MenuItem;
import Model.Order.Order;
import Model.OrderItem.OrderItem;


public class OrderController {

	private static Connection connection = Singleton.getInstance().getConnection();

	public ArrayList<Order> showOrderList(){
		ArrayList<Order> orderItem = new ArrayList<>();
		
		String query = "SELECT * FROM `order` WHERE orderStatus = 'Pending'";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int orderId = rs.getInt("orderId");
				int orderUser = rs.getInt("orderUser");
				String orderItems = rs.getString("orderItems");
				String orderStatus = rs.getString("orderStatus");
				Timestamp timestamp = rs.getTimestamp("orderDate");
				int orderTotal = rs.getInt("orderTotal");
				orderItem.add(new Order(orderId,orderUser,orderItems,orderStatus,timestamp,orderTotal));
						
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderItem;
		
	}
	public void insertOrderItem(ArrayList<OrderItem> orderList) {
		String query = "INSERT INTO orderitem(orderId, menuItemId, quantity) VALUES (?,?,?)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			for (OrderItem order : orderList) {
				ps.setInt(1, order.getOrderId());
				ps.setInt(2, order.getMenuItemId());
				ps.setInt(3, order.getQuantity());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertOrder(ArrayList<Order> orders) {
		
		String query = "INSERT INTO `order`(orderId,orderUser,orderItems,orderStatus,orderDate,orderTotal) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			for (Order order : orders) {
				ps.setInt(1, order.getOrderId());
				ps.setInt(2, order.getOrderUser());
				ps.setString(3, order.getOrderItems());
				ps.setString(4, order.getOrderStatus());
				ps.setTimestamp(5, order.getOrderDate());
				ps.setInt(6, order.getOrderTotal());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public void insertReceipt(int receiptId,int receiptOrder, int receiptPaymentAmount, Timestamp receiptPaymentDate,
			String receiptPaymentType) {
		String query = "INSERT INTO receipt(receiptId,receiptOrder, receiptPaymentAmount, receiptPaymentDate,receiptPaymentType) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, receiptId);
			ps.setInt(2, receiptOrder);
			ps.setInt(3, receiptPaymentAmount);
			ps.setTimestamp(4, receiptPaymentDate);
			ps.setString(5, receiptPaymentType);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getMenuItemById(int id) {
		String menuName = "";
		String query = "SELECT menuItemName FROM menuItem WHERE menuItemId = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				menuName = rs.getString("menuItemName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuName;
	}

	public int getMenuPriceByMenuId(int menuId) {
		int totalAmount = 0 ;
		String query = "SELECT menuItemPrice FROM menuItem WHERE menuItemId = ?";

		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, menuId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				totalAmount = rs.getInt("menuItemPrice");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalAmount;
	}
	public String getUserById(int id) {
		String user = "";
		String query = "SELECT userName FROM user WHERE userId = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) user = rs.getString("userName");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public int getLastId() {
		int lastId = 1;
		String query = "SELECT orderId FROM orderitem ORDER BY orderId DESC LIMIT 1";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				lastId = rs.getInt("orderId");
		} catch (SQLException e) {
		}
		return lastId;
	}



}
