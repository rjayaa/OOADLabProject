package View.Customer;

import java.util.ArrayList;

import Controller.Order.OrderController;
import Model.OrderItem.OrderItem;
import View.Global.QuantityInput;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerCartPage extends Stage {

	// component
	private BorderPane root;
	private Scene scene;
	private Button btnBackToMenu, btnPayment;

	// controller
	OrderController orderController = new OrderController();

	public CustomerCartPage(ArrayList<OrderItem> orderItem) {
		super(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 800, 600);
		this.setTitle("Mystic Grills");
		this.setScene(scene);

		VBox wrap = new VBox(40);

		TableView<OrderItem> menuCartTable = createCartTable(orderItem);
		menuCartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		HBox hbox = new HBox(20);
		btnBackToMenu = new Button("Back");
		btnPayment = new Button("Checkout");
		btnBackToMenu.setPrefSize(100, 50);
		btnPayment.setPrefSize(100, 50);
		hbox.getChildren().addAll(btnBackToMenu, btnPayment);
		hbox.setAlignment(Pos.CENTER);
		wrap.getChildren().addAll(menuCartTable, hbox);
		wrap.setAlignment(Pos.CENTER);
		root.setTop(wrap);

		btnBackToMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				CustomerMenuPage cmp = new CustomerMenuPage();
				cmp.showAndWait();
				Stage currentStage = (Stage) btnBackToMenu.getScene().getWindow();
				currentStage.close();
			}
		});
		btnPayment.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				CustomerPaymentPage ptv = new CustomerPaymentPage(orderItem);
				ptv.showAndWait();
				Stage currentStage = (Stage) btnPayment.getScene().getWindow();
				currentStage.close();
			}
		});

	}

	private TableView<OrderItem> createCartTable(ArrayList<OrderItem> orderItems) {
		TableView<OrderItem> menuCartTable = new TableView<>();

		TableColumn<OrderItem, String> menuName = new TableColumn<>("Menu Name");
		menuName.setCellValueFactory(data -> {
			int menuItemId = data.getValue().getMenuItemId();
			String menuItemName = orderController.getMenuItemById(menuItemId);

			return new SimpleStringProperty(menuItemName);
		});

		TableColumn<OrderItem, Integer> menuQuantity = new TableColumn<>("Quantity");
		menuQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		TableColumn<OrderItem, Void> actionColumn = new TableColumn<>("Action Column");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private final Button updateButton = new Button("Update Quantity");
			private final Button deleteButton = new Button("Delete");

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
					setOnMouseClicked(null);
				} else {

					HBox buttons = new HBox(updateButton, deleteButton);
					buttons.setSpacing(5);
					setGraphic(buttons);

					updateButton.setOnAction(event -> {
						OrderItem selectedItem = getTableView().getItems().get(getIndex());
						QuantityInput qi = new QuantityInput(selectedItem);
						qi.showAndWait();
						if (qi.isBtnPressed()) {
							selectedItem.setQuantity(qi.getQty());
							getTableView().refresh();

						}
					});

					deleteButton.setOnAction(event -> {
						OrderItem selectedItem = getTableView().getItems().get(getIndex());
						getTableView().getItems().remove(selectedItem);
						orderItems.remove(selectedItem);

					});
				}
			}
		});

		menuCartTable.getColumns().add(menuName);
		menuCartTable.getColumns().add(menuQuantity);
		menuCartTable.getColumns().add(actionColumn);
		menuCartTable.getItems().addAll(orderItems);

		return menuCartTable;
	}

}
