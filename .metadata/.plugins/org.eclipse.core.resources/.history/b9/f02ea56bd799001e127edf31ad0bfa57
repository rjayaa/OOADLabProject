package Controller.Order;

import Model.MenuItem.MenuItem;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuantityInput extends Stage {
	private Scene scene;
	private BorderPane root;
	private VBox contentArea;

	

	// Component
	private Label lblFoodName, lblQty;
	private TextField txtFoodName, txtQty;
	private Button btnOrder, btnCancel;
	private Alert alert = new Alert(AlertType.ERROR);
	private int qty;
	private boolean btnPressed = false;
	private OrderController orderController = new OrderController();

	public QuantityInput(MenuItem curr) {
		this.setTitle("Input Quantity");
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 300, 200);
		this.setScene(scene);

		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));

		lblFoodName = new Label("Food Name :");
		txtFoodName = new TextField(curr.getMenuItemName());
		txtFoodName.setDisable(true);

		lblQty = new Label("Quantity :");
		txtQty = new TextField();

		btnOrder = new Button("Confirm");
		btnOrder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					String validateQty = txtQty.getText();
					if (validateQty.isEmpty()) {

						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Quantity be Empty");
						alert.showAndWait();
					} else if (!validateQty.matches("\\d+")) {
						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Please enter a valid quantity (only numbers allowed)");
						alert.showAndWait();
					} else if (Integer.parseInt(validateQty) <= 0) {
						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Quantity should be greater than zero");
						alert.showAndWait();
					}else {
						Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
						successAlert.setTitle("Success");
						successAlert.setHeaderText(null);
						successAlert.setContentText("Successfully added to cart");
						successAlert.showAndWait();
						qty = Integer.parseInt(txtQty.getText()); 
						btnPressed = true;
						close();
					}
				} catch (NumberFormatException e) {

				}
			}
		});

		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e -> this.close());

		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnOrder, btnCancel);
		buttonBox.setAlignment(Pos.CENTER);
		contentArea.getChildren().addAll(lblFoodName, txtFoodName, lblQty, txtQty, buttonBox);
		root.setCenter(contentArea);
	}
	
	
	public QuantityInput(OrderItem curr) {
		this.setTitle("Input Quantity");
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 300, 200);
		this.setScene(scene);

		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));

		lblFoodName = new Label("Food Name :");
		txtFoodName = new TextField(orderController.getMenuItemById(curr.getMenuItemId()));
		txtFoodName.setDisable(true);

		lblQty = new Label("Quantity :");
		txtQty = new TextField();

		btnOrder = new Button("Confirm");
		btnOrder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					String validateQty = txtQty.getText();
					if (validateQty.isEmpty()) {

						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Quantity be Empty");
						alert.showAndWait();
					} else if (!validateQty.matches("\\d+")) {
						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Please enter a valid quantity (only numbers allowed)");
						alert.showAndWait();
					} else if (Integer.parseInt(validateQty) <= 0) {
						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Quantity should be greater than zero");
						alert.showAndWait();
					}else {
						Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
						successAlert.setTitle("Success");
						successAlert.setHeaderText(null);
						successAlert.setContentText("Successfully added to cart");
						successAlert.showAndWait();
						qty = Integer.parseInt(txtQty.getText()); 
						btnPressed = true;
						close();
					}
				} catch (NumberFormatException e) {

				}
			}
		});

		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e -> this.close());

		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnOrder, btnCancel);
		buttonBox.setAlignment(Pos.CENTER);
		contentArea.getChildren().addAll(lblFoodName, txtFoodName, lblQty, txtQty, buttonBox);
		root.setCenter(contentArea);
	}

	public int getQty() {
		return qty;
	}

	public boolean isBtnPressed() {
		return btnPressed;
	}
}
