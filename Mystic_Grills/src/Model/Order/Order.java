package Model.Order;

import java.sql.Timestamp;

public class Order {
	private int orderId;
	private int orderUser;
	private String orderItems;
	private String orderStatus;
	private Timestamp orderDate;
	private int orderTotal;
	
	
	public Order(int orderId, int orderUser, String orderItems, String orderStatus, Timestamp orderDate,
			int orderTotal) {
		this.orderId = orderId;
		this.orderUser = orderUser;
		this.orderItems = orderItems;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(int orderUser) {
		this.orderUser = orderUser;
	}

	public String getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(String orderItems) {
		this.orderItems = orderItems;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	
	
	
	
}
