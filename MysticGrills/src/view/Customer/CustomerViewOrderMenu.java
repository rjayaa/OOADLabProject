package view.Customer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import model.Order;

import java.sql.Timestamp;
import java.util.ArrayList;

import controller.customerController.*;

public class CustomerViewOrderMenu {
    private TableView<Order> tableView;
    private Button button;
    private CustomerController customerController;

    public CustomerViewOrderMenu(Stage stage, CustomerController customerController) {
        this.customerController = customerController;

        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        button = new Button("Back");
        button.setPrefWidth(100);
        button.setPrefHeight(50);

        TableColumn<Order, Integer> orderIdColumn = new TableColumn<>("Order ID");
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        TableColumn<Order, Integer> orderUserColumn = new TableColumn<>("Order User");
        orderUserColumn.setCellValueFactory(new PropertyValueFactory<>("orderUser"));

        TableColumn<Order, ArrayList<String>> orderItemsColumn = new TableColumn<>("Order Items");
        orderItemsColumn.setCellValueFactory(new PropertyValueFactory<>("orderItems"));

        TableColumn<Order, String> orderStatusColumn = new TableColumn<>("Order Status");
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        TableColumn<Order, Timestamp> orderDateColumn = new TableColumn<>("Order Date");
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        TableColumn<Order, Integer> orderTotalColumn = new TableColumn<>("Order Total");
        orderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));

        tableView.getColumns().add(orderIdColumn);
        tableView.getColumns().add(orderUserColumn);
        tableView.getColumns().add(orderItemsColumn);
        tableView.getColumns().add(orderStatusColumn);
        tableView.getColumns().add(orderDateColumn);
        tableView.getColumns().add(orderTotalColumn);

        tableView.getItems().addAll(customerController.getAllOrderedOrders());

        VBox vbox = new VBox(tableView, button);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setTitle("Customer Order View");
        stage.setScene(scene);
        stage.show();
    }
}