package view.Waiter;

import java.sql.Timestamp;
import java.util.ArrayList;

import controller.waiterController.WaiterController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order;

public class WaiterView {
    private WaiterController waiterController;
    private TableView<Order> tableView;
    private Button button;

    public WaiterView(Stage stage, WaiterController waiterController) {
        this.waiterController = waiterController;
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        button = new Button("Back");
        button.setPrefWidth(100);
        button.setPrefHeight(50);
        button.setOnAction(event -> {
            stage.close();
        });
        Button paidButton = new Button("Paid");
        paidButton.setPrefWidth(100);
        paidButton.setPrefHeight(50);

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

        tableView.getItems().addAll(waiterController.getAllOrder());

        TextField orderIdField = new TextField();
        orderIdField.setPromptText("Order ID");

        TextField orderUserField = new TextField();
        orderUserField.setPromptText("Order User");

        TextField orderItemsField = new TextField();
        orderItemsField.setPromptText("Order Items");

        TextField orderStatusField = new TextField();
        orderStatusField.setPromptText("Order Status");

        TextField orderDateField = new TextField();
        orderDateField.setPromptText("Order Date");

        TextField orderTotalField = new TextField();
        orderTotalField.setPromptText("Order Total");

        orderIdField.setDisable(true);
        orderUserField.setDisable(true);
        orderItemsField.setDisable(true);
        orderStatusField.setDisable(true);
        orderDateField.setDisable(true);
        orderTotalField.setDisable(true);

        GridPane gridPane = new GridPane();
        gridPane.add(orderIdField, 0, 0);
        gridPane.add(orderUserField, 1, 0);
        gridPane.add(orderItemsField, 2, 0);
        gridPane.add(orderStatusField, 0, 1);
        gridPane.add(orderDateField, 1, 1);
        gridPane.add(orderTotalField, 2, 1);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Order order = newSelection;
                orderIdField.setText(String.valueOf(order.getOrderId()));
                orderUserField.setText(String.valueOf(order.getOrderUser()));
                orderItemsField.setText(order.getOrderItems().toString());
                orderStatusField.setText(order.getOrderStatus());
                orderDateField.setText(order.getOrderDate().toString());
                orderTotalField.setText(String.valueOf(order.getOrderTotal()));

            }
        });

        paidButton.setOnAction(event -> {
            Order selectedOrder = tableView.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                waiterController.updateOrderStatusById(selectedOrder.getOrderId());
                orderIdField.clear();
                orderUserField.clear();
                orderItemsField.clear();
                orderStatusField.clear();
                orderDateField.clear();
                orderTotalField.clear();
                tableView.getItems().clear();
                tableView.getItems().addAll(waiterController.getAllOrder());
            }
        });

        HBox buttonBox = new HBox(button, paidButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(tableView, gridPane, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setTitle("Waiter View");
        stage.setScene(scene);
        stage.show();
    }
}
