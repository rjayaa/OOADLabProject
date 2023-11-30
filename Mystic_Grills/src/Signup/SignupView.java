package Signup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

	// component
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
		btnSignup.setOnAction(null);
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
