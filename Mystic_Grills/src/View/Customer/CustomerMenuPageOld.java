package View.Customer;

import java.util.ArrayList;

import Controller.Menu.MenuController;
import Controller.Order.OrderController;
import Controller.Order.OrderInformationInput;
import DBConnection.Singleton;
import Model.MenuItem.MenuItem;
import Model.Order.Order;
import Model.OrderItem.OrderItem;
import Model.User.User;
import View.Global.QuantityInput;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerMenuPageOld extends Stage {

	private BorderPane root;
	private VBox contentArea;
	private ArrayList<MenuItem> orderCart = new ArrayList<>();
	private ArrayList<Integer> orderQuantity = new ArrayList<>();
	MenuController menuController = new MenuController();
	OrderController orderController = new OrderController();

	private Label lblMenu, lblCart;

	private User currentUser;

	public CustomerMenuPageOld() {
		super(StageStyle.DECORATED);
		this.currentUser = Singleton.getInstance().getCurrentUser();
		root = new BorderPane();
		Scene scene = new Scene(root, 800, 600);
		this.setScene(scene);

		contentArea = new VBox(40);
		contentArea.setPadding(new Insets(10));

		lblMenu = new Label("Our Menu!");
		lblMenu.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		contentArea.setAlignment(Pos.CENTER);
		TableView<MenuItem> menuItemsTable = createMenuItemsTable();
		loadMenuItemsData(menuItemsTable);
		double rowHeight = 40;
		menuItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		menuItemsTable.prefHeightProperty().bind(Bindings.size(menuItemsTable.getItems()).multiply(rowHeight));
		contentArea.getChildren().addAll(lblMenu, menuItemsTable);

		root.setTop(contentArea);

	}

	private void loadMenuItemsData(TableView<MenuItem> menuItemsTable) {
		ArrayList<MenuItem> menuItemList = menuController.showMenuItems();
		menuItemsTable.getItems().setAll(menuItemList);
	}

	private void showMenuList() {
		contentArea.getChildren().clear();
		lblMenu = new Label("Our Menu!");
		lblCart = new Label("Cart!");
		lblMenu.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lblCart.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		TableView<MenuItem> menuItemsTable = createMenuItemsTable();
		TableView<MenuItem> cartItemsTable = createCartItemsTable(orderCart, orderQuantity);
		loadMenuItemsData(menuItemsTable);
		double rowHeight = 40;
		menuItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		menuItemsTable.prefHeightProperty().bind(Bindings.size(menuItemsTable.getItems()).multiply(rowHeight));
		cartItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		cartItemsTable.prefHeightProperty().bind(Bindings.size(menuItemsTable.getItems()).multiply(rowHeight));
		contentArea.getChildren().addAll(lblMenu,menuItemsTable);
		contentArea.getChildren().addAll(cartItemsTable);

		if (!cartItemsTable.getItems().isEmpty()) {
			submitOrder();
		}
	}

	private TableView<MenuItem> createMenuItemsTable() {
		TableView<MenuItem> menuItemsTable = new TableView<>();

		TableColumn<MenuItem, String> idColumn = new TableColumn<>("Menu ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemID"));

		TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Menu Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemName"));

		TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Menu Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemDescription"));

		TableColumn<MenuItem, Integer> priceColumn = new TableColumn<>("Menu Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemPrice"));

		TableColumn<MenuItem, Void> actionColumn = new TableColumn<>("Add Order");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private Button addButton = new Button("Add");

			{
				addButton.setOnAction(e -> {
//					setGraphic(quantityInput);
					MenuItem curr = getTableView().getItems().get(getIndex());
					QuantityInput qi = new QuantityInput(curr);
					qi.showAndWait();

					if (qi.isBtnPressed()) {
						orderCart.add(curr);
						orderQuantity.add(qi.getQty());
						reloadPage();
					}

				});
			}

			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || getIndex() < 0 || getIndex() > menuItemsTable.getItems().size()) {
					setGraphic(null);
					return;
				}

				setGraphic(addButton);
			}
		});

		menuItemsTable.getColumns().add(nameColumn);
		menuItemsTable.getColumns().add(descriptionColumn);
		menuItemsTable.getColumns().add(priceColumn);
		menuItemsTable.getColumns().add(actionColumn);

		return menuItemsTable;
	}

	private TableView<MenuItem> createCartItemsTable(ArrayList<MenuItem> foodList, ArrayList<Integer> orderQuantity) {
		TableView<MenuItem> menuItemsTable = new TableView<>();

		TableColumn<MenuItem, String> idColumn = new TableColumn<>("Menu ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemID"));

		TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Menu Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));

		TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Menu Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription"));

		TableColumn<MenuItem, Integer> priceColumn = new TableColumn<>("Menu Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));

		TableColumn<MenuItem, Integer> qtyColumn = new TableColumn<>("Quantity");
		qtyColumn.setCellValueFactory(cellData -> {
			int index = foodList.indexOf(cellData.getValue());
			return index >= 0 ? new SimpleIntegerProperty(orderQuantity.get(index)).asObject() : null;
		});

		TableColumn<MenuItem, Void> actionColumn = new TableColumn<>("Add Order");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private Button deleteButton = new Button("Delete");

			{
				deleteButton.setOnAction(e -> {
					int targetIndex = getTableRow().getIndex();
					orderCart.remove(targetIndex);
					orderQuantity.remove(targetIndex);
					reloadPage();
				});
			}

			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || getIndex() < 0 || getIndex() > menuItemsTable.getItems().size()) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
			}
		});

		menuItemsTable.getColumns().add(nameColumn);
		menuItemsTable.getColumns().add(descriptionColumn);
		menuItemsTable.getColumns().add(priceColumn);
		menuItemsTable.getColumns().add(qtyColumn);
		menuItemsTable.getColumns().add(actionColumn);

		menuItemsTable.getItems().addAll(foodList);

		return menuItemsTable;
	}

	private void submitOrder() {
		Button submitBtn = new Button("Proceed Order");
		contentArea.getChildren().addAll(submitBtn);

		submitBtn.setOnAction(e -> {
			OrderInformationInput inputInfo = new OrderInformationInput(orderCart, orderQuantity, currentUser);
			inputInfo.show();

		});
	}

	private void loadOrderListData(TableView<OrderItem> orderListTable) {
		ArrayList<OrderItem> orderList = orderController.showOrderList();
		orderListTable.getItems().setAll(orderList);
	}

	private void showCustomerOrderList() {
		contentArea.getChildren().clear();

		Label titleLabel = new Label("Order List");
		contentArea.getChildren().addAll(titleLabel);

		TableView<OrderItem> orderListTable = createOrderListTable();
		loadOrderListData(orderListTable);
		orderListTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		contentArea.getChildren().addAll(orderListTable);
	}

	private TableView<OrderItem> createOrderListTable() {
		TableView<OrderItem> orderListTable = new TableView<>();

		TableColumn<OrderItem, String> orderIDColumn = new TableColumn<>("Order ID");
		orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));

		TableColumn<OrderItem, String> userIDColumn = new TableColumn<>("User ID");
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));

		TableColumn<OrderItem, String> paymentTypeColumn = new TableColumn<>("Payment Type");
		paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("PaymentType"));

		TableColumn<OrderItem, Integer> paymentAmountColumn = new TableColumn<>("Payment Amount");
		paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("PaymentAmount"));

		TableColumn<OrderItem, String> statusColumn = new TableColumn<>("Payment Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("PaymentStatus"));

		TableColumn<OrderItem, Void> actionColumn = new TableColumn<>("Order Details");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private Button linkButton = new Button("See Details");
			private HBox container = new HBox(10);

			{
				linkButton.setOnAction(e -> {
					OrderItem o = getTableView().getItems().get(getIndex());
					showOrderDetails(o);
				});
				container.getChildren().addAll(linkButton);
			}
		});

		orderListTable.getColumns().add(orderIDColumn);
		orderListTable.getColumns().add(userIDColumn);
		orderListTable.getColumns().add(paymentTypeColumn);
		orderListTable.getColumns().add(paymentAmountColumn);
		orderListTable.getColumns().add(statusColumn);
		orderListTable.getColumns().add(actionColumn);

		return orderListTable;
	}

	private void loadOrderDetailsData(TableView<Order> orderDetailsTable, OrderItem order) {
		ArrayList<Order> orderDetails = orderController.showOrderDetailsByOrder(order);
		orderDetailsTable.getItems().setAll(orderDetails);
	}

	private void showOrderDetails(OrderItem order) {
		contentArea.getChildren().clear();

		Label titleLabel = new Label("Order Details");
		contentArea.getChildren().addAll(titleLabel);

		TableView<Order> orderDetailsTable = createOrderDetailsTable(order);
		loadOrderDetailsData(orderDetailsTable, order);
		orderDetailsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		contentArea.getChildren().addAll(orderDetailsTable);

		Button backBtn = new Button("<< Back");
		contentArea.getChildren().addAll(backBtn);
		backBtn.setOnAction(e -> {
			showCustomerOrderList();
		});
	}

	private TableView<Order> createOrderDetailsTable(OrderItem order) {
		TableView<Order> orderDetailsTable = new TableView<>();

		TableColumn<Order, String> orderDetailsIDColumn = new TableColumn<>("ID");
		orderDetailsIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Order, String> orderIDColumn = new TableColumn<>("Order ID");
		orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("Order ID"));

		TableColumn<Order, String> menuIDColumn = new TableColumn<>("Menu ID");
		menuIDColumn.setCellValueFactory(new PropertyValueFactory<>("Menu ID"));

		TableColumn<Order, Integer> qtyColumn = new TableColumn<>("Quantity");
		qtyColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

		TableColumn<Order, Void> actionColumn = new TableColumn<>("Update Data");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private Button editButton = new Button("Edit");
			private HBox container = new HBox(10);

			{
				editButton.setOnAction(e -> {
					Order od = getTableView().getItems().get(getIndex());
					updateOrderDetails(order, od);
				});
				container.getChildren().addAll(editButton);
			}
		});

		orderDetailsTable.getColumns().add(orderDetailsIDColumn);
		orderDetailsTable.getColumns().add(orderIDColumn);
		orderDetailsTable.getColumns().add(menuIDColumn);
		orderDetailsTable.getColumns().add(qtyColumn);
		orderDetailsTable.getColumns().add(actionColumn);

		return orderDetailsTable;
	}

	private void updateOrderDetails(OrderItem order, Order od) {
		contentArea.getChildren().clear();

		HBox container = new HBox(10);
		Label prompt = new Label("Please Input New Quantity :");
		TextField qtyInput = new TextField();
		container.getChildren().addAll(prompt, qtyInput);

		Button updateBtn = new Button("Update Data");
		updateBtn.setOnAction(e -> {
////			od.setQuantity(Integer.parseInt(qtyInput.getText()));
//			orderController.updateOrderDetails(od, Integer.parseInt(qtyInput.getText()));
//			showOrderDetails(order);
		});

	}

	private void showCustomerProfile() {
		contentArea.getChildren().clear();
	}

	private void reloadPage() {
		Platform.runLater(() -> {
			showMenuList();
		});
	}
}