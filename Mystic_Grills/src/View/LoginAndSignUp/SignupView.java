package View.LoginAndSignUp;

import java.util.Random;

import Controller.User.UserController;
import Model.User;
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

public class SignupView extends Stage {
	private Scene scene;
	private BorderPane root;
	private VBox contentArea;

	// Controller
	private static UserController usercontroller = new UserController();
	private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);

	// Component
	private Label lblUsername, lblEmail, lblPassword, lblConfirmPassword;
	private TextField txtUsername, txtEmail, txtPassword, txtConfirmPassword;
	private Button btnSignup, btnCancel;

	public SignupView() {
		this.setTitle("Register");
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 400, 250);
		this.setScene(scene);

		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));

		lblUsername = new Label("Username");
		txtUsername = new TextField();

		lblEmail = new Label("Email");
		txtEmail = new TextField();

		lblPassword = new Label("Password");
		txtPassword = new TextField();

		lblConfirmPassword = new Label("Confirm Password");
		txtConfirmPassword = new TextField();

		btnSignup = new Button("Signup");
		btnSignup.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					Random rand = new Random();
					String userid = String.format("US%03d", rand.nextInt(1000));
					String username = txtUsername.getText();
					String email = txtEmail.getText();
					String password = txtPassword.getText();
					String confirmPassword = txtConfirmPassword.getText();
					 
					
					
					if(usercontroller.inputValidation(username,email,password,confirmPassword) == 1) {
						errorAlert.setTitle("Error");
						errorAlert.setHeaderText(null);
						errorAlert.setContentText("Username cannot be empty!");
						errorAlert.showAndWait();
					}else if(usercontroller.inputValidation(username,email,password,confirmPassword) == 2) {
						errorAlert.setTitle("Error");
						errorAlert.setHeaderText(null);
						errorAlert.setContentText("Email already exist!, Please login!");
						errorAlert.showAndWait();
					}else if(usercontroller.inputValidation(username,email,password,confirmPassword) == 3) {
						errorAlert.setTitle("Error");
						errorAlert.setHeaderText(null);
						errorAlert.setContentText("Email cannot be empty");
						errorAlert.showAndWait();
					}else if(usercontroller.inputValidation(username,email,password,confirmPassword) == 4) {
						errorAlert.setTitle("Error");
						errorAlert.setHeaderText(null);
						errorAlert.setContentText("Password should be at least 6 characters!");
						errorAlert.showAndWait();
					}else if(usercontroller.inputValidation(username,email,password,confirmPassword) == 5) {
						errorAlert.setTitle("Error");
						errorAlert.setHeaderText(null);
						errorAlert.setContentText("Password and confirm password do not match. Please make sure both passwords are identical.");
						errorAlert.showAndWait();
					}
					else {					
						User user = new User(userid,"Customer",username,email,password);
						usercontroller.insertUser(user);
						txtUsername.clear();
						txtEmail.clear();
						txtPassword.clear();
						txtConfirmPassword.clear();
					}
					
					
					
				}catch(NumberFormatException e) {
					
				}
			}
		});
		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e->this.close());

		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnSignup,btnCancel);
		buttonBox.setAlignment(Pos.CENTER);
		contentArea.getChildren().addAll(lblUsername, txtUsername, lblEmail, txtEmail, lblPassword, txtPassword,
				lblConfirmPassword, txtConfirmPassword, buttonBox);
		root.setCenter(contentArea);
		
	}

}
