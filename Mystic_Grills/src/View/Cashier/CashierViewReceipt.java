package View.Cashier;

import java.sql.Timestamp;
import java.util.ArrayList;

import Controller.Order.OrderController;
import Model.MenuItem.MenuItem;
import Model.Order.Order;
import Model.OrderItem.OrderItem;
import Model.Receipt.Receipt;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CashierViewReceipt extends Stage {
	private Scene scene;
	private BorderPane root;
	private VBox contentArea;

	// Component
	private Label lblOrderId, lblPaymentType, lblPaymentAmount;
	private TextField txtOrderId, txtPaymentType, txtPaymentAmount;
	private Button btnBack;
	private Alert alert = new Alert(AlertType.ERROR);
	private int qty;
	private boolean btnPressed = false;
	private OrderController orderController = new OrderController();

	public CashierViewReceipt(int orderId) {
		this.setTitle("View Receipt");
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 300, 400);
		this.setScene(scene);

		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));
		
		Receipt receipt = orderController.getReceiptByOrderId(orderId);

		lblOrderId = new Label("Order ID :");
		txtOrderId = new TextField(Integer.toString(receipt.getReceiptOrder()));
		txtOrderId.setDisable(true);

		lblPaymentType = new Label("Payment Type :");
		txtPaymentType = new TextField(receipt.getReceiptPaymentType());
		txtPaymentType.setDisable(true);
		
		lblPaymentAmount = new Label("Payment Amount :");
		txtPaymentAmount = new TextField(Integer.toString(receipt.getReceiptPaymentAmount()));
		txtPaymentAmount.setDisable(true);
		
		TableView<Order> menuTable = createOrderTable();
		loadMenuItemsData(menuTable, orderId);
		menuTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		btnBack = new Button("Back");
		btnBack.setOnAction(e -> this.close());

		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnBack);
		buttonBox.setAlignment(Pos.CENTER);
		contentArea.getChildren().addAll(lblOrderId, txtOrderId, lblPaymentType, txtPaymentType, lblPaymentAmount, txtPaymentAmount, menuTable, buttonBox);
		root.setCenter(contentArea);
	}
	
	private  TableView<Order> createOrderTable(){
		TableView<Order> orderTable = new TableView<>();
		
		TableColumn<Order, String> orderName = new TableColumn<>("Menu Items");
		orderName.setCellValueFactory(new PropertyValueFactory("orderItems"));
		
		TableColumn<Order, String> orderPrice = new TableColumn<>("Menu Price");
		orderPrice.setCellValueFactory(new PropertyValueFactory("orderStatus"));
		
		TableColumn<Order, Integer> orderQty = new TableColumn<>("Quantity");
		orderQty.setCellValueFactory(new PropertyValueFactory("orderTotal"));
		
		orderTable.getColumns().add(orderName);
		orderTable.getColumns().add(orderPrice);
		orderTable.getColumns().add(orderQty);
		
		return orderTable;
	}
	
	private void loadMenuItemsData(TableView<Order> menuOrderTable, int orderId) {
		ArrayList<Order> showOrderList = orderController.showOrderListById(orderId);
		menuOrderTable.getItems().setAll(showOrderList);

	}

	public int getQty() {
		return qty;
	}

	public boolean isBtnPressed() {
		return btnPressed;
	}
}

