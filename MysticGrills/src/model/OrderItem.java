package model;

import controller.customerController.CustomerController;

public class OrderItem {
    private int orderId;
    private int menuItemId;
    private int quantity;
    private CustomerController controller;

    public OrderItem(int orderId, int menuItemId, int quantity) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.controller = new CustomerController();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.valueOf(controller.getMenuItemById(menuItemId));
    }

}
