package view;

import controller.LoginController;
import controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterView {
    private TextField usernameField;
    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button registerButton;
    private GridPane gridPane;
    private Button cancelButton;
    private RegisterController controller;
    private Stage stage;

    public RegisterView(Stage stage, RegisterController controller) {
        this.stage = stage;
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 0);

        usernameField = new TextField();
        gridPane.add(usernameField, 1, 0);

        Label emailLabel = new Label("Email:");
        gridPane.add(emailLabel, 0, 1);

        emailField = new TextField();
        gridPane.add(emailField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 2);

        passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        gridPane.add(confirmPasswordLabel, 0, 3);

        confirmPasswordField = new PasswordField();
        gridPane.add(confirmPasswordField, 1, 3);

        registerButton = new Button("Register");
        gridPane.add(registerButton, 1, 4);
        cancelButton = new Button("Cancel");
        gridPane.add(cancelButton, 0, 4);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(cancelButton, registerButton);
        gridPane.add(buttonBox, 1, 4);

        cancelButton.setOnAction(e -> {
            LoginView loginView = new LoginView(stage);
            stage.close();
            loginView.show(stage);
        });
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            controller.register(username, email, password, confirmPassword);
        });
    }

    public void show(Stage stage) {
        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}