package view.Customer;

import controller.customerController.CustomerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class CustomerView {
    private HBox hbox;
    private Stage stage;
    private CustomerController customerController;

    public CustomerView(Stage stage, CustomerController customerController) {
        this.stage = stage;
        this.customerController = customerController;
        hbox = new HBox(5);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER);

        Button addOrderButton = new Button("Add Order");
        addOrderButton.setOnAction(e -> {
            Stage customerAddOrderStage = new Stage();
            CustomerViewOrder customerAddOrder = new CustomerViewOrder(customerAddOrderStage, customerController);
            customerAddOrder.show(customerAddOrderStage);
        });
        Button viewOrderButton = new Button("View Order");
        viewOrderButton.setOnAction(e -> {
            new CustomerViewOrderMenu(new Stage(), customerController);
        });
        Button viewReceiptButton = new Button("View Receipt");
        viewReceiptButton.setOnAction(e -> {
            new CustomerViewReceipt(new Stage(), customerController);
        });
        addOrderButton.setPrefWidth(100);
        addOrderButton.setPrefHeight(50);
        viewOrderButton.setPrefWidth(100);
        viewOrderButton.setPrefHeight(50);
        viewReceiptButton.setPrefWidth(100);
        viewReceiptButton.setPrefHeight(50);

        hbox.getChildren().addAll(addOrderButton, viewOrderButton, viewReceiptButton);
    }

    public void show(Stage stage) {
        Scene scene = new Scene(hbox, 600, 500);
        stage.setTitle("Customer View");
        stage.setScene(scene);
        stage.show();
    }
}
