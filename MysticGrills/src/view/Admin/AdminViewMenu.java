package view.Admin;

import controller.adminController.AdminController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.MenuItem;
import model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminViewMenu {
    private Stage stage;
    private VBox vbox;
    private AdminController adminController;
    private TextField itemIdTextField = new TextField();
    private TextField itemNameTextField = new TextField();
    private TextField itemDescriptionTextField = new TextField();
    private TextField itemPriceTextField = new TextField();

    public AdminViewMenu(Stage stage, AdminController adminController) {
        this.stage = stage;
        this.adminController = adminController;
        vbox = new VBox(5);
        vbox.setPadding(new Insets(10));
        TableView<MenuItem> tableView = createTableView();
        loadMenuItem(tableView);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.getChildren().addAll(tableView);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        Label itemIdLabel = new Label("Menu Item ID:");
        TextField itemIdTextField = new TextField();
        itemIdTextField.setDisable(true);

        Label itemNameLabel = new Label("Menu Item Name:");
        TextField itemNameTextField = new TextField();

        Label itemDescriptionLabel = new Label("Menu Item Description:");
        TextField itemDescriptionTextField = new TextField();

        Label itemPriceLabel = new Label("Menu Item Price:");
        TextField itemPriceTextField = new TextField();

        gridPane.add(itemIdLabel, 0, 0);
        gridPane.add(itemIdTextField, 1, 0);
        gridPane.add(itemNameLabel, 0, 1);
        gridPane.add(itemNameTextField, 1, 1);
        gridPane.add(itemDescriptionLabel, 0, 2);
        gridPane.add(itemDescriptionTextField, 1, 2);
        gridPane.add(itemPriceLabel, 0, 3);
        gridPane.add(itemPriceTextField, 1, 3);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                itemIdTextField.setText(String.valueOf(newSelection.getMenuItemId()));
                itemNameTextField.setText(newSelection.getMenuItemName());
                itemDescriptionTextField.setText(newSelection.getMenuItemDescription());
                itemPriceTextField.setText(String.valueOf(newSelection.getMenuItemPrice()));
            }
        });

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String itemName = itemNameTextField.getText();
            String itemDescription = itemDescriptionTextField.getText();
            String itemPriceText = itemPriceTextField.getText();

            if (!itemPriceText.isEmpty()) {
                try {
                    int itemPrice = Integer.parseInt(itemPriceText);

                    try {
                        adminController.addMenuItem(itemName, itemDescription, itemPrice);
                        loadMenuItem(tableView);
                        itemIdTextField.clear();
                        itemNameTextField.clear();
                        itemDescriptionTextField.clear();
                        itemPriceTextField.clear();
                    } catch (Exception e1) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error: " + e1.getMessage());

                        alert.showAndWait();
                    }
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error: Item Price must be a valid integer.");

                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: Item Price cannot be empty.");

                alert.showAndWait();
            }
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            String itemIdText = itemIdTextField.getText();
            String itemName = itemNameTextField.getText();
            String itemDescription = itemDescriptionTextField.getText();
            String itemPriceText = itemPriceTextField.getText();

            if (!itemIdText.isEmpty()) {
                try {
                    int itemId = Integer.parseInt(itemIdText);

                    if (!itemPriceText.isEmpty()) {
                        try {
                            int itemPrice = Integer.parseInt(itemPriceText);

                            try {
                                adminController.updateMenuItem(itemId, itemName, itemDescription, itemPrice);
                                loadMenuItem(tableView);
                                itemIdTextField.clear();
                                itemNameTextField.clear();
                                itemDescriptionTextField.clear();
                                itemPriceTextField.clear();
                            } catch (Exception e1) {
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText(null);
                                alert.setContentText("Error: " + e1.getMessage());

                                alert.showAndWait();
                            }
                        } catch (NumberFormatException ex) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Error: Item Price must be a valid integer.");

                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error: Item Price cannot be empty.");

                        alert.showAndWait();
                    }
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error: Item ID must be a valid integer.");

                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: Item ID cannot be empty.");

                alert.showAndWait();
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            String itemIdText = itemIdTextField.getText();

            if (!itemIdText.isEmpty()) {
                try {
                    int itemId = Integer.parseInt(itemIdText);
                    adminController.deleteMenuItem(itemId);
                    loadMenuItem(tableView);
                    itemIdTextField.clear();
                    itemNameTextField.clear();
                    itemDescriptionTextField.clear();
                    itemPriceTextField.clear();
                } catch (Exception ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error: " + ex.getMessage());

                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: Item ID cannot be empty.");

                alert.showAndWait();
            }
        });
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            itemIdTextField.clear();
            itemNameTextField.clear();
            itemDescriptionTextField.clear();
            itemPriceTextField.clear();
        });

        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, clearButton);

        vbox.getChildren().addAll(gridPane, buttonBox);

    }

    private TableView<MenuItem> createTableView() {
        TableView<MenuItem> tableView = new TableView<>();

        TableColumn<MenuItem, Integer> menuItemId = new TableColumn<>("Menu Item ID");
        menuItemId.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));

        TableColumn<MenuItem, String> menuItemName = new TableColumn<>("Menu Item Name");
        menuItemName.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));

        TableColumn<MenuItem, String> menuItemDescription = new TableColumn<>("Menu Item Description");
        menuItemDescription.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription"));

        TableColumn<MenuItem, Integer> menuItemPrice = new TableColumn<>("Menu Item Price");
        menuItemPrice.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));

        tableView.getColumns().addAll(menuItemId, menuItemName, menuItemDescription, menuItemPrice);

        return tableView;
    }

    private void loadMenuItem(TableView<MenuItem> tableView) {
        tableView.getItems().clear();
        tableView.getItems().addAll(adminController.getAllMenuItem());
    }

    public void show(Stage stage) {
        Scene scene = new Scene(vbox, 800, 600);
        stage.setTitle("Admin View Menu");
        stage.setScene(scene);
        stage.show();
    }

}
