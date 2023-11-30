package LoginAndSignup;



import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginView extends Stage {
	private Scene scene;
	private BorderPane root;
	private VBox contentArea;
	private Button btnLogin, btnSignup;
	private Label lblEmail, lblPassword;
	private TextField txtEmail, txtPassword;
	
	public LoginView() {
		this.setTitle("Login");
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root,300,200);
		this.setScene(scene);
		
		contentArea = new VBox(10);
		contentArea.setPadding(new Insets(20));
		lblEmail = new Label("Email");
		txtEmail = new TextField();
		lblPassword = new Label("Password");
		txtPassword = new TextField();
		
		btnLogin = new Button("Login");
		btnLogin.setOnAction(null);
		
		btnSignup = new Button("Signup");
		btnSignup.setOnAction(null);
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnLogin,btnSignup);
		contentArea.getChildren().addAll(lblEmail, txtEmail
				,lblPassword,txtPassword, buttonBox
				);
		root.setCenter(contentArea);

	}

}
