package Controller.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBConnection.Singleton;
import Model.FoodItem.FoodItem;
import Model.Order.Order;
import Model.OrderDetails.OrderDetails;



public class OrderController {
	
	private static Connection connection = Singleton.getInstance().getConnection();
	
	public void insertOrder(Order order) {
		String query = "INSERT INTO order(orderID, userID, paymentType, paymentAmount, status) VALUES (?,?,?,?,?)";
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, order.getOrderID());
			ps.setString(2, order.getUserID());
			ps.setString(3, order.getPaymentType());
			ps.setInt(4, order.getPaymentAmount());
			ps.setString(5, order.getStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertOrderDetails(OrderDetails orderDetail) {
		String query = "INSERT INTO orderdetails(orderDetailsID, orderID, menuID, quantity) VALUES (?,?,?,?)";
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, orderDetail.getOrderDetailsID());
			ps.setString(2, orderDetail.getOrderID());
			ps.setString(3, orderDetail.getMenuID());
			ps.setInt(4, orderDetail.getQuantity());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Order> showOrderList() {
		ArrayList<Order> orderList = new ArrayList<>();
		String query = "SELECT * FROM `order`";

		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				String orderId = resultSet.getString("orderID");
				String userID = resultSet.getString("userID");
				String paymentType = resultSet.getString("paymentType");
				int paymentAmount = resultSet.getInt("paymentAmount");
				String paymentStatus = resultSet.getString("status");
				orderList.add(new Order(orderId,userID, paymentType, paymentAmount, paymentStatus));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	
	public ArrayList<OrderDetails> showOrderDetailsByOrder(Order order) {
		ArrayList<OrderDetails> orderDetails = new ArrayList<>();
		String query = "SELECT * FROM orderdetails WHERE orderID = '" + order.getOrderID() + "'";

		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				String orderDetailsID = resultSet.getString("orderDetailsID");
				String orderID = resultSet.getString("orderID");
				String menuID = resultSet.getString("menuID");
				int quantity = resultSet.getInt("quantity");
				orderDetails.add(new OrderDetails(orderDetailsID, order.getOrderID(), menuID, quantity));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orderDetails;
	}
	
	public void updateOrderDetails(OrderDetails od, Integer quantity) {
		String query = "UPDATE orderdetails SET quantity = ? WHERE orderDetailsID = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, quantity);
			ps.setString(2, od.getOrderDetailsID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}