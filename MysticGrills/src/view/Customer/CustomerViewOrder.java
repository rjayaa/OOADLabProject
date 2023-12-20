package view.Customer;

import controller.customerController.CustomerController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import model.OrderItem;

import model.MenuItem;

public class CustomerViewOrder {
    private VBox vbox;
    private TableView<MenuItem> tableView;
    private Button buttonBox;
    private CustomerController customerController;
    private ArrayList<OrderItem> orderItem = new ArrayList<>();

    public CustomerViewOrder(Stage stage, CustomerController customerController) {
        this.customerController = customerController;
        vbox = new VBox(5);
        tableView = createTableView();
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
        itemNameTextField.setDisable(true);

        Label itemDescriptionLabel = new Label("Menu Item Description:");
        TextField itemDescriptionTextField = new TextField();
        itemDescriptionTextField.setDisable(true);

        Label itemPriceLabel = new Label("Menu Item Price:");
        TextField itemPriceTextField = new TextField();
        itemPriceTextField.setDisable(true);

        Label quantityLabel = new Label("Quantity:");
        TextField quantityTextField = new TextField();

        gridPane.add(itemIdLabel, 0, 0);
        gridPane.add(itemIdTextField, 1, 0);
        gridPane.add(itemNameLabel, 0, 1);
        gridPane.add(itemNameTextField, 1, 1);
        gridPane.add(itemDescriptionLabel, 0, 2);
        gridPane.add(itemDescriptionTextField, 1, 2);
        gridPane.add(itemPriceLabel, 0, 3);
        gridPane.add(itemPriceTextField, 1, 3);
        gridPane.add(quantityLabel, 0, 4);
        gridPane.add(quantityTextField, 1, 4);

        buttonBox = new Button("Add");
        buttonBox.setOnAction(e -> {
            String quantityText = quantityTextField.getText();

            if (quantityText.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Quantity");
                alert.setHeaderText(null);
                alert.setContentText("Quantity cannot be empty");
                alert.showAndWait();
            } else {
                MenuItem selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int quantity = Integer.parseInt(quantityText);

                    customerController.validateQuantity(quantity);

                    if (quantity >= 0) {
                        OrderItem orderItem = new OrderItem(customerController.getLastId() + 1,
                                customerController.getMenuItemIdByName(selectedItem.getMenuItemName()), quantity);
                        this.orderItem.add(orderItem);
                    }
                }
            }
        });
        buttonBox.setMinWidth(100);
        buttonBox.setMinHeight(50);

        Button viewCartButton = new Button("View Cart");
        viewCartButton.setMinWidth(100);
        viewCartButton.setMinHeight(50);
        viewCartButton.setOnAction(e -> {
            Stage customerViewCartStage = new Stage();
            CustomerViewCart customerViewCart = new CustomerViewCart(customerViewCartStage, orderItem);
            VBox cartLayout = customerViewCart.getLayout();

            Scene cartScene = new Scene(cartLayout, 800, 600);
            stage.setScene(cartScene);
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                itemIdTextField.setText(String.valueOf(newSelection.getMenuItemId()));
                itemNameTextField.setText(newSelection.getMenuItemName());
                itemDescriptionTextField.setText(newSelection.getMenuItemDescription());
                itemPriceTextField.setText(String.valueOf(newSelection.getMenuItemPrice()));
                quantityTextField.setText("");
            }
        });

        vbox.getChildren().addAll(gridPane, buttonBox, viewCartButton);
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
        tableView.getItems().addAll(customerController.getAllMenuItem());
    }

    public void show(Stage stage) {
        Scene scene = new Scene(vbox, 800, 600);
        stage.setTitle("Customer View Order");
        stage.setScene(scene);
        stage.show();
    }

}
