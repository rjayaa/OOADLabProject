package view.Customer;

import java.sql.Timestamp;
import java.util.ArrayList;

import controller.customerController.CustomerController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Receipt;
import util.Singleton;

public class CustomerViewReceipt {
    private CustomerController customerController;
    private TableView<Receipt> tableView;

    public CustomerViewReceipt(Stage stage, CustomerController customerController) {
        this.customerController = customerController;

        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Receipt, Integer> receiptIdColumn = new TableColumn<>("Receipt ID");
        receiptIdColumn.setCellValueFactory(new PropertyValueFactory<>("receiptId"));

        TableColumn<Receipt, Integer> receiptOrderIdColumn = new TableColumn<>("Order ID");
        receiptOrderIdColumn.setCellValueFactory(new PropertyValueFactory<>("receiptOrderId"));

        TableColumn<Receipt, Integer> receiptPaymentAmountColumn = new TableColumn<>("Payment Amount");
        receiptPaymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentAmount"));

        TableColumn<Receipt, Timestamp> receiptPaymentDateColumn = new TableColumn<>("Payment Date");
        receiptPaymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentDate"));

        TableColumn<Receipt, String> receiptPaymentTypeColumn = new TableColumn<>("Payment Type");
        receiptPaymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentType"));

        tableView.getColumns().addAll(receiptIdColumn, receiptOrderIdColumn, receiptPaymentAmountColumn,
                receiptPaymentDateColumn, receiptPaymentTypeColumn);

        ArrayList<Receipt> receipts = customerController
                .getAllReceiptsByUserId(Singleton.getInstance().getCurrentUser().getUserId());
        for (Receipt receipt : receipts) {
            tableView.getItems().add(receipt);
        }

        Button backButton = new Button("Back");
        backButton.setPrefSize(100, 50);
        backButton.setOnAction(event -> stage.close());

        VBox vbox = new VBox(tableView, backButton);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setTitle("Customer Receipt");
        stage.setScene(scene);
        stage.show();
    }
}
