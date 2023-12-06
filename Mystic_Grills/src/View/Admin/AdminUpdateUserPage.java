package View.Admin;

import Controller.User.UserController;
import Model.User.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminUpdateUserPage extends Stage {
	private Scene scene;
	private BorderPane root;
	private VBox contentArea;

	// component
	private Label lblUsername;
	private TextField txtUsername;
	private ComboBox<String> dropDown;
	private Button btnSubmit;
	private boolean btnPressed = false;
	private Alert alert = new Alert(AlertType.INFORMATION);

	// controller
	private UserController uc = new UserController();

	public AdminUpdateUserPage(User user) {
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 300, 200);
		this.setTitle("Update Role");
		this.setScene(scene);

		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));

		lblUsername = new Label("Username : ");
		txtUsername = new TextField(user.getUserName());
		txtUsername.setDisable(true);

		dropDown = new ComboBox<>();
		dropDown.getItems().addAll("Admin", "Chef", "Waiter", "Cashier", "Customer");
		dropDown.setValue("Customer");

		btnSubmit = new Button("Update");
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				uc.updateUser(user.getUserName(), dropDown.getValue().toString());
				alert.setTitle("Success");
				alert.setHeaderText(null);
				alert.setContentText("Role Updated!");
				alert.showAndWait();
				
			}
		});

		contentArea.getChildren().addAll(lblUsername, txtUsername, dropDown, btnSubmit);

		root.setTop(contentArea);

	}
	
	public String getNewRole() {
		return dropDown.getValue().toString();
	}
	public boolean isBtnPressed() {
		return btnPressed;
	}
}
