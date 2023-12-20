package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private int orderUser;
    private ArrayList<String> orderItems;
    private String orderStatus;
    private Timestamp orderDate;
    private int orderTotal;

    public Order(int orderId, int orderUser, ArrayList<String> orderItems, String orderStatus, Timestamp orderDate,
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

    public ArrayList<String> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<String> orderItems) {
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

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

}
