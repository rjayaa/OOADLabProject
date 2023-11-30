package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBConnection.Singleton;
import Model.FoodItem;
import Model.Order;
import Model.OrderDetails;



public class OrderController {
	
	public void insertOrder(Order order) {
		String query = "INSERT INTO order(OrderID, UserID, PaymentType, PaymentAmount,Status)" + 
				"VALUES ('" + order.getOrderID() + "','" + order.getUserID() +
				"','" + order.getPaymentType() +"'," + order.getPaymentAmount() + ",'" +
				order.getStatus() + "')";
		
		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertOrderDetails(OrderDetails orderDetail) {
		String query = "INSERT INTO orderdetails(OrderDetailsID, OrderID, MenuID, Quantity)" + 
				"VALUES ('" + orderDetail.getOrderDetailsID() + "','" + orderDetail.getOrderID() +
				"','" + orderDetail.getMenuID() + "'," + orderDetail.getQuantity() +")";
		
		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Order> showOrderList() {
		ArrayList<Order> orderList = new ArrayList<>();
		String query = "SELECT * FROM order";

		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				String orderId = resultSet.getString("OrderID");
				String userID = resultSet.getString("UserID");
				String paymentType = resultSet.getString("PaymentType");
				int paymentAmount = resultSet.getInt("PaymentAmount");
				String paymentStatus = resultSet.getString("PaymentStatus");
				orderList.add(new Order(orderId,userID, paymentType, paymentAmount, paymentStatus));
			}
//			statement.executeQ(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	
	public ArrayList<OrderDetails> showOrderDetailsByOrder(Order order) {
		ArrayList<OrderDetails> orderDetails = new ArrayList<>();
		String query = "SELECT * FROM orderdetails WHERE OrderID = '" + order.getOrderID() + "'";

		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				String orderDetailsID = resultSet.getString("OrderDetailsID");
				String menuID = resultSet.getString("MenuID");
				int quantity = resultSet.getInt("Quantity");
				orderDetails.add(new OrderDetails(orderDetailsID, order.getOrderID(), menuID, quantity));
			}
//			statement.executeUpdate(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orderDetails;
	}
	
	public void updateOrderDetails(OrderDetails od, Integer quantity) {
		String query = "UPDATE orderdetails SET Quantity = " + quantity +
				" WHERE OrderDetailsID = '" + od.getOrderDetailsID() + "')";
		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
