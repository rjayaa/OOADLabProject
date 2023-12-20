package view.Customer;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order;
import model.OrderItem;
import model.Receipt;
import util.Singleton;
import controller.customerController.CustomerController;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CustomerViewCart {
    private ArrayList<OrderItem> orderItems;
    private TableView<OrderItem> tableView;
    private VBox layout;
    private Stage stage;
    private controller.customerController.CustomerController customerController;

    public CustomerViewCart(Stage stage, ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.stage = stage;
        this.customerController = new CustomerController();
        tableView = new TableView<>();
        layout = new VBox(10);

        TableColumn<OrderItem, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        TableColumn<OrderItem, Integer> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));

        TableColumn<OrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableView.getColumns().addAll(idColumn, nameColumn, quantityColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.getItems().addAll("Debit", "Credit", "Transfer");
        comboBox1.setValue("Debit");

        Button checkoutButton = new Button("Checkout");

        checkoutButton.setOnAction(e -> {
            ArrayList<OrderItem> items = new ArrayList<>(tableView.getItems());
            customerController.insertOrderItem(items);

            ArrayList<String> orderItemsString = new ArrayList<>();
            int totalQuantity = 0;
            for (OrderItem item : items) {
                orderItemsString.add(item.toString());
                totalQuantity += item.getQuantity();
            }

            Order order = new Order(customerController.getLastIdOrder() + 1,
                    Singleton.getInstance().getCurrentUser().getUserId(), orderItemsString,
                    "Pending", new Timestamp(System.currentTimeMillis()),
                    totalQuantity);

            customerController.insertOrder(order);
            Receipt receipt = new Receipt(1,
                    order.getOrderId(),
                    customerController.getPriceByOrderId(order.getOrderId()) - 1, order.getOrderDate(),
                    comboBox1.getValue());
            System.out.println(customerController.getPriceByOrderId(order.getOrderId()));
            customerController.insertReceipt(receipt);
            orderItems.clear();
            updateTableView();
        });

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(tableView, comboBox1, checkoutButton);

        updateTableView();
    }

    public void updateTableView() {
        tableView.getItems().clear();
        tableView.getItems().addAll(orderItems);
    }

    public VBox getLayout() {
        return layout;
    }
}