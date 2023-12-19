package view;

import controller.LoginController;
import controller.RegisterController;
import controller.adminController.AdminController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Admin.AdminView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginView {
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;
    private VBox vbox;
    private Stage stage;

    public LoginView(Stage stage) {
        this.stage = stage;
        vbox = new VBox(5);
        vbox.setPadding(new Insets(10));

        emailField = new TextField();
        emailField.setPromptText("Email");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin());

        registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            Stage registerStage = new Stage();
            RegisterController registerController = new RegisterController();
            RegisterView registerView = new RegisterView(registerStage, registerController);
            registerView.show(registerStage);
            stage.hide();
        });
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(registerButton, loginButton);

        vbox.getChildren().addAll(emailField, passwordField, buttonBox);
    }

    public void show(Stage stage) {

        Scene scene = new Scene(vbox, 200, 150);
        stage.setTitle("Mystic Grills");
        stage.setScene(scene);
        stage.show();
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();
        LoginController controller = new LoginController();
        if (controller.login(email, password)) {
            if (controller.getRoleByEmail(email).equals("Admin")) {
                Stage adminStage = new Stage();
                AdminController adminController = new AdminController();
                AdminView adminView = new AdminView(adminStage, adminController);
                adminView.show(stage);
            }
        }

    }
}