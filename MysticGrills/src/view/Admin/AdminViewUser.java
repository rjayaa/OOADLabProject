package view.Admin;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import java.util.ArrayList;

import controller.adminController.AdminController;

public class AdminViewUser {
    private Stage stage;
    private VBox vbox;
    private AdminController adminController;

    public AdminViewUser(Stage stage, AdminController adminController) {
        this.stage = stage;
        this.adminController = adminController;
        vbox = new VBox(5);
        vbox.setPadding(new Insets(10));
        TableView<User> tableView = createTableView();
        loadUser(tableView);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.getChildren().addAll(tableView);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label idLabel = new Label("User ID:");
        TextField idField = new TextField();
        idField.setPromptText("ID");
        idField.setDisable(true);

        Label roleLabel = new Label("User Role:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Customer", "Chef", "Waiter", "Cashier");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setDisable(true);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setDisable(true);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(roleLabel, 0, 1);
        gridPane.add(roleComboBox, 1, 1);
        gridPane.add(usernameLabel, 2, 0);
        gridPane.add(usernameField, 3, 0);
        gridPane.add(emailLabel, 2, 1);
        gridPane.add(emailField, 3, 1);

        vbox.getChildren().add(gridPane);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idField.setText(String.valueOf(newSelection.getUserId()));
                roleComboBox.setValue(newSelection.getUserRole());
                usernameField.setText(newSelection.getUserName());
                emailField.setText(newSelection.getUserEmail());
            }
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            adminController.updateUser(Integer.parseInt(idField.getText()), roleComboBox.getValue());
            loadUser(tableView);
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            adminController.deleteUser(Integer.parseInt(idField.getText()));
            loadUser(tableView);
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(updateButton, deleteButton);
        vbox.getChildren().add(buttonBox);
    }

    public void show(Stage stage) {
        Scene scene = new Scene(vbox, 800, 600);
        stage.setTitle("Admin View User");
        stage.setScene(scene);
        stage.show();
    }

    private TableView<User> createTableView() {
        TableView<User> tableView = new TableView<>();

        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(roleColumn);
        tableView.getColumns().add(usernameColumn);
        tableView.getColumns().add(emailColumn);

        return tableView;
    }

    private void loadUser(TableView<User> userTable) {
        ArrayList<User> user = adminController.getAllUser();
        userTable.getItems().setAll(user);
    }

}