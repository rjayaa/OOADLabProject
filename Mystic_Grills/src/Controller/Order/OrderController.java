package Controller.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.Singleton;
import Model.Order.Order;
import Model.OrderItem.OrderItem;



public class OrderController {
	
	private static Connection connection = Singleton.getInstance().getConnection();
	
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
	
	
	public void insertOrder(ArrayList<Order> orders){
		int gege;
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
			// TODO: handle exception
		}
	}
	
	
	public void insertOrderItems(ArrayList<OrderItem> orderItemList, int orderId) {
//	    String query = "INSERT INTO orderItem(orderId, menuItemId, quantity) VALUES (?,?,?)";
//
//	    try (PreparedStatement ps = Singleton.getInstance().getConnection().prepareStatement(query)) {
//	        for (OrderItem orderItem : orderItemList) {
//	            ps.setInt(1, orderId);
//	            ps.setInt(2, orderItem.getMenuItemId());
//	            ps.setInt(3, orderItem.getQuantity());
//	            ps.executeUpdate();
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
	}

	public void insertReceipt(int orderId, int paymentAmount, String paymentType) {
	    String query = "INSERT INTO receipt(orderId, receiptPaymentAmount, receiptPaymentType) VALUES (?,?,?)";

	    try (PreparedStatement ps = Singleton.getInstance().getConnection().prepareStatement(query)) {
	        ps.setInt(1, orderId);
	        ps.setInt(2, paymentAmount);
	        ps.setString(3, paymentType);
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
	        if(rs.next()) {
	            menuName = rs.getString("menuItemName");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return menuName;
	}
	public int getLastId() {
		int lastId = 1;
		String query = "SELECT orderId FROM orderitem ORDER BY orderId DESC LIMIT 1";
		
		 	try {
				PreparedStatement ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) lastId = rs.getInt("orderId");
			} catch (SQLException e) {
			}
		return lastId;	
	}

	
//	public ArrayList<MenuItem> showMenuItems() {
//		ArrayList<MenuItem> foodItem = new ArrayList<>();
//		String query = "SELECT * FROM ";
//
//		try(Connection connection = Singleton.getInstance().getConnection()){
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(query);
//			while(resultSet.next()) {
//				String id = resultSet.getString("menuItemID");
//				String name = resultSet.getString("menuItemName");
//				String desc = resultSet.getString("menuItemDescription");
//				int price = resultSet.getInt("menuItemPrice");
//				foodItem.add(new MenuItem(id,name,desc,price));
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return foodItem;
//	}
//	
	

//	public void insertOrderDetails(Order orderDetail) {
//		String query = "INSERT INTO orderdetails(orderDetailsID, orderID, menuID, quantity) VALUES (?,?,?,?)";
//		
//		try (PreparedStatement ps = Singleton.getInstance().getConnection().prepareStatement(query)) {
//			ps.setString(1, orderDetail.getOrderDetailsID());
//			ps.setString(2, orderDetail.getOrderID());
//			ps.setString(3, orderDetail.getMenuID());
//			ps.setInt(4, orderDetail.getQuantity());
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public ArrayList<OrderItem> showOrderList() {
		ArrayList<OrderItem> orderList = new ArrayList<>();
//		String query = "SELECT * FROM `order`";
//
//		try(Connection connection = Singleton.getInstance().getConnection()){
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(query);
//			while(resultSet.next()) {
//				String orderId = resultSet.getString("orderID");
//				String userID = resultSet.getString("userID");
//				String paymentType = resultSet.getString("paymentType");
//				int paymentAmount = resultSet.getInt("paymentAmount");
//				String paymentStatus = resultSet.getString("status");
//				orderList.add(new Order(orderId,userID, paymentType, paymentAmount, paymentStatus));
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
		return orderList;
	}
	
	
	public ArrayList<Order> showOrderDetailsByOrder(OrderItem order) {
		ArrayList<Order> orderDetails = new ArrayList<>();
//		String query = "SELECT * FROM orderdetails WHERE orderID = '" + order.getOrderID() + "'";
//
//		try(Connection connection = Singleton.getInstance().getConnection()){
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(query);
//			while(resultSet.next()) {
//				String orderDetailsID = resultSet.getString("orderDetailsID");
//				String orderID = resultSet.getString("orderID");
//				String menuID = resultSet.getString("menuID");
//				int quantity = resultSet.getInt("quantity");
//				orderDetails.add(new OrderDetails(orderDetailsID, order.getOrderID(), menuID, quantity));
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
		return orderDetails;
	}
	
	
}
