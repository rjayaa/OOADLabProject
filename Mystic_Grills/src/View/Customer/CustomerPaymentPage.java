package View.Customer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Controller.Order.OrderController;
import DBConnection.Singleton;
import Model.Order.Order;
import Model.OrderItem.OrderItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerPaymentPage extends Stage {

	// framework
	private Scene scene;
	private BorderPane root;
	private VBox contentArea;

	// Component
	private Label lblPayment;
	private Button btnConfirm, btnCancel;
	private Alert alert = new Alert(AlertType.INFORMATION);
	// Controller
	OrderController orderController = new OrderController();

	// Object
	private Date now = new Date();
	private Timestamp timeStampNow = new Timestamp(now.getTime());
	private ArrayList<Order> order = new ArrayList<>();

	int currUserId = Singleton.getInstance().getCurrentUser().getUserId();

	public CustomerPaymentPage(ArrayList<OrderItem> orderList) {

		this.setTitle("Order Information");
		this.initStyle(StageStyle.DECORATED);

		root = new BorderPane();
		scene = new Scene(root, 300, 150);
		this.setScene(scene);

		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));

		lblPayment = new Label("Input Payment Type :");
		ToggleGroup paymentType = new ToggleGroup();

		RadioButton cashBtn = new RadioButton("Cash");
		cashBtn.setToggleGroup(paymentType);

		RadioButton debitBtn = new RadioButton("Debit");
		debitBtn.setToggleGroup(paymentType);

		RadioButton creditBtn = new RadioButton("Credit");
		creditBtn.setToggleGroup(paymentType);

		btnConfirm = new Button("Confirm");
		btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				RadioButton selectedRadioButton = (RadioButton) paymentType.getSelectedToggle();
				int orderId = orderList.get(0).getOrderId();
				int totalAmount = 0;
				if (selectedRadioButton != null) {

					orderController.insertOrderItem(orderList); // insert order item
					for (OrderItem ord : orderList) {
						Order newOrder = new Order(ord.getOrderId(), currUserId,
								orderController.getMenuItemById(ord.getMenuItemId()), "Pending", timeStampNow,
								ord.getQuantity());
						int menuPrice = orderController.getMenuPriceByMenuId(ord.getMenuItemId());
						int tempAmount = ord.getQuantity() * menuPrice;
						totalAmount += tempAmount;
						order.add(newOrder);
					}

					orderController.insertOrder(order); // insert order
					orderController.insertReceipt(orderController.getLastId() + 1, orderId, totalAmount, timeStampNow,
							selectedRadioButton.getText()); // insert receipt
					alert.setTitle("Success");
					alert.setHeaderText(null);
					alert.setContentText("Payment Succed");
					alert.showAndWait();
					
					CustomerOrderStatusPage cos = new CustomerOrderStatusPage();
					cos.show();
					Stage currentStage = (Stage) btnConfirm.getScene().getWindow(); 
					currentStage.close();
				}
			}
		});

		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e -> this.close());

		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnConfirm, btnCancel);
		buttonBox.setAlignment(Pos.CENTER);
		contentArea.getChildren().addAll(lblPayment, cashBtn, debitBtn, creditBtn, buttonBox);
		root.setCenter(contentArea);
	}
}
