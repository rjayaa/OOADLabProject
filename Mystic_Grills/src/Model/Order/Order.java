package Model.Order;

import java.util.ArrayList;

public class Order {
	private String orderID;
	private String userID;
	private String paymentType;
	private Integer paymentAmount;
	private String status;
	
	public Order(String orderID, String userID, String paymentType, Integer paymentAmount, String status) {
		this.orderID = orderID;
		this.userID = userID;
		this.paymentType = paymentType;
		this.paymentAmount = paymentAmount;
		this.status = status;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
