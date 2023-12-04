package View.Global;

import java.util.Random;

import Controller.User.UserController;
import Model.FoodItem.FoodItem;
import Model.User.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

	// Controller
//	private static UserController usercontroller = new UserController();
//	private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);

	// Component
	private Label lblFoodName, lblQty;
	private TextField txtFoodName, txtQty;
	private Button btnOrder, btnCancel;
	
	private int qty = 0;
	private boolean btnPressed = false;
	
	public QuantityInput(FoodItem curr) {
		this.setTitle("Input Quantity");
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 400, 250);
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
					qty = Integer.parseInt(txtQty.getText());
					if(qty > 0) {
						btnPressed = true;
						close();
					}
					
				}catch(NumberFormatException e) {
					
				}
			}
		});
		
		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e->this.close());

		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnOrder,btnCancel);
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