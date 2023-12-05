package View.Customer;

import java.util.ArrayList;

import Controller.Food.FoodController;
import Model.FoodItem.FoodItem;
import Model.Order.Order;
import View.Global.QuantityInput;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dummyCustomerPage extends Stage {

	// wireframe
	private BorderPane root;
	private Scene scene;

	// component
	private Label lblMenu;
	ArrayList<Order> selectedItems = new ArrayList<>();
	// controller
	FoodController fc = new FoodController();

	public dummyCustomerPage() {
		super(StageStyle.DECORATED);
		// wireframe
		root = new BorderPane();
		scene = new Scene(root, 800, 600);
		this.setScene(scene);

		VBox vb = new VBox(40);

		lblMenu = new Label("Our Menu!");
		lblMenu.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		TableView<FoodItem> menuItemsTable = createMenuItemsTable();
		double rowHeight = 40;
		menuItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		menuItemsTable.prefHeightProperty().bind(Bindings.size(menuItemsTable.getItems()).multiply(rowHeight));

		loadMenuItemsData(menuItemsTable);
		vb.getChildren().addAll(lblMenu, menuItemsTable);
		vb.setAlignment(Pos.TOP_CENTER);

		root.setTop(vb);

	}

	private void loadMenuItemsData(TableView<FoodItem> menuItemsTable) {
		ArrayList<FoodItem> menuItemList = fc.showMenuItems();
		menuItemsTable.getItems().setAll(menuItemList);
	}

	private TableView<FoodItem> createMenuItemsTable() {
		TableView<FoodItem> menuItemsTable = new TableView<>();

		TableColumn<FoodItem, String> menuName = new TableColumn<>("Menu name");
		menuName.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));
		TableColumn<FoodItem, String> menuDescription = new TableColumn<>("Menu Description");
		menuDescription.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription"));
		TableColumn<FoodItem, Integer> menuPrice = new TableColumn<>("Menu Price");
		menuPrice.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));
		TableColumn<FoodItem, Void> actionColumn = new TableColumn<>("Action Column");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private final Button addButton = new Button("Add To Cart");
			private String orderId;
			private int quantity = 0;

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
					setOnMouseClicked(null);
				} else {

					addButton.setOnAction(event -> {
						FoodItem curr = getTableView().getItems().get(getIndex());
						QuantityInput qi = new QuantityInput(curr);
						qi.showAndWait();
						if(qi.isBtnPressed()) {
							System.out.println(qi.getQty());
						}
					});
					setGraphic(addButton);
				}
			}
		});

		menuItemsTable.getColumns().add(menuName);
		menuItemsTable.getColumns().add(menuDescription);
		menuItemsTable.getColumns().add(menuPrice);
		menuItemsTable.getColumns().add(actionColumn);

		return menuItemsTable;
	}

}
