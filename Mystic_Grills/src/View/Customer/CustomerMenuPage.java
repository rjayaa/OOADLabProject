package View.Customer;

import java.util.ArrayList;
import java.util.Random;

import Controller.Menu.MenuController;
import Controller.Order.OrderController;
import Model.MenuItem.MenuItem;
import Model.OrderItem.OrderItem;
import View.Global.QuantityInput;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerMenuPage extends Stage {

	// wireframe
	private BorderPane root;
	private Scene scene;

	// component
	private Label lblMenu;
	private Button btnViewCart;
	ArrayList<OrderItem> selectedItems = new ArrayList<>();
	// controller
	MenuController fc = new MenuController();
	

	public CustomerMenuPage() {
		super(StageStyle.DECORATED);
	
		root = new BorderPane();
		scene = new Scene(root, 800, 600);
		this.setScene(scene);

		VBox vb = new VBox(40);

		lblMenu = new Label("Our Menu!");
		lblMenu.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		TableView<MenuItem> menuItemsTable = createMenuItemsTable();
		double rowHeight = 40;
		menuItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		menuItemsTable.prefHeightProperty().bind(Bindings.size(menuItemsTable.getItems()).multiply(rowHeight));

		loadMenuItemsData(menuItemsTable);
		btnViewCart = new Button("View My Cart");
		btnViewCart.setPrefSize(100,50);
		
		vb.getChildren().addAll(lblMenu, menuItemsTable, btnViewCart);
		vb.setAlignment(Pos.TOP_CENTER);
		
		
		btnViewCart.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(selectedItems.isEmpty()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(null);
					alert.setHeaderText(null);
					alert.setContentText("You haven't add any menu yet!");
					alert.showAndWait();
				}else {
					CustomerCartPage ccp = new CustomerCartPage(selectedItems);
					ccp.showAndWait();
					Stage currentStage = (Stage) btnViewCart.getScene().getWindow(); 
					currentStage.close();
				}
				
			}
		});

		

		root.setTop(vb);
		
	}

	private void loadMenuItemsData(TableView<MenuItem> menuItemsTable) {
		ArrayList<MenuItem> menuItemList = fc.showMenuItems();
		menuItemsTable.getItems().setAll(menuItemList);
	}

	private TableView<MenuItem> createMenuItemsTable() {
		TableView<MenuItem> menuItemsTable = new TableView<>();

		TableColumn<MenuItem, String> menuName = new TableColumn<>("Menu name");
		menuName.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));
		TableColumn<MenuItem, String> menuDescription = new TableColumn<>("Menu Description");
		menuDescription.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription"));
		TableColumn<MenuItem, Integer> menuPrice = new TableColumn<>("Menu Price");
		menuPrice.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));
		TableColumn<MenuItem, Void> actionColumn = new TableColumn<>("Action Column");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private final Button addButton = new Button("Add To Cart");

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
					setOnMouseClicked(null);
				} else {

					addButton.setOnAction(event -> {
						MenuItem curr = getTableView().getItems().get(getIndex());
						
						QuantityInput qi = new QuantityInput(curr);
						qi.showAndWait();
						if (qi.isBtnPressed()) {
							int orderId = fc.getLastId() + 1;
							OrderItem orderItem = new OrderItem(orderId, curr.getMenuItemId(),qi.getQty());
							selectedItems.add(orderItem);
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
