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
	
	public ArrayList<Order> checkOrderList(int currentOrderId, String orderName){
		ArrayList<Order> orderItem = new ArrayList<>();
		
		String query = "SELECT * FROM `order` WHERE orderId = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, currentOrderId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				updateReceipt(currentOrderId);
			}
			else {
				deleteReceipt(currentOrderId);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderItem;
		
	}
	
	public ArrayList<Order> showOrderListForCustomer(int userId){
		ArrayList<Order> orderItem = new ArrayList<>();
		
		String query = "SELECT * FROM `order` WHERE orderUser = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
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
	
	public ArrayList<Order> showOrderListForCashier(){
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
	
	public void updateOrderStatusByCashier(int orderId, String orderItem) {
		String query = "UPDATE `order` SET orderStatus = 'Paid' WHERE orderId = ? AND orderItems = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setString(2, orderItem);	
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Order> showOrderListForChef(){
		ArrayList<Order> orderItem = new ArrayList<>();
		
		String query = "SELECT * FROM `order` WHERE orderStatus = 'Paid' OR orderStatus = 'Pending'";
		
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
	
	public void updateOrderStatusByChef(int orderId, String orderItem) {
		String query = "UPDATE `order` SET orderStatus = 'Prepared' WHERE orderId = ? AND orderItems = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setString(2, orderItem);
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Order> showOrderListForWaiter(){
		ArrayList<Order> orderItem = new ArrayList<>();
		
		String query = "SELECT * FROM `order` WHERE orderStatus = 'Prepared' OR orderStatus = 'Pending'";
		
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
	
	public void updateOrderStatusByWaiter(int orderId, String orderItem) {
		String query = "UPDATE `order` SET orderStatus = 'Served' WHERE orderId = ? AND orderItems = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setString(2, orderItem);
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public void updateReceipt(int receiptOrder) {
		
		int totalPrice = getPriceByOrderId(receiptOrder);
		
		String query = "UPDATE `receipt` SET receiptPaymentAmount = ? WHERE receiptOrder = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, totalPrice);
			ps.setInt(2, receiptOrder);
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOrder(int orderId, String orderItem) {
		String query = "DELETE FROM `order` WHERE orderId = ? AND orderItems = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setString(2, orderItem);
			ps.executeUpdate();
			
			deleteOrderItem(orderId, getItemIdFromItemName(orderItem));
			checkOrderList(orderId, orderItem);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deleteOrderItem(int orderId, int orderItemId) {
		String query = "DELETE FROM `orderitem` WHERE orderId = ? AND menuItemId = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setInt(2, orderItemId);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deleteReceipt(int orderId) {
		String query = "DELETE FROM `receipt` WHERE receiptOrder = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
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

	public int getStatusByOrderId(int orderId) {
		String query = "SELECT orderStatus FROM `order` WHERE orderId = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("orderStatus") == "Pending") {
					return 0;
				}
				else if(rs.getString("orderStatus") == "Paid") {
					return 1;
				}
				else {
					return -1;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return -1;
	}
	
	public int getItemIdFromItemName(String itemName) {
		String query = "SELECT * FROM `menuitem` WHERE menuItemName = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, itemName);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("menuItemId");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public int getPriceByOrderId(int orderId) {
		String query = "SELECT SUM(o.quantity * m.menuItemPrice) AS TotalPrice FROM `orderitem` AS o JOIN `menuitem` AS m ON o.menuItemId=m.menuItemId WHERE o.orderId = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("TotalPrice");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

}
