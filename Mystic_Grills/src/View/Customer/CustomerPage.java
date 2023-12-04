package View.Customer;



import java.util.ArrayList;

import Controller.Food.FoodController;
import Controller.Order.OrderController;
import Controller.Order.OrderInformationInput;
import DBConnection.Singleton;
import Model.FoodItem.FoodItem;
import Model.Order.Order;
import Model.OrderDetails.OrderDetails;
import Model.User.User;
import View.Global.QuantityInput;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class CustomerPage extends Stage {
	
	private BorderPane root;
	private VBox contentArea;
	private MenuBar menuBar;
	private ArrayList<FoodItem> orderCart = new ArrayList<>();
	private ArrayList<Integer> orderQuantity = new ArrayList<>();
	FoodController menuController = new FoodController();
	OrderController orderController = new OrderController();
	
	private User currentUser;
	public CustomerPage() {
		super(StageStyle.DECORATED);
		this.currentUser = Singleton.getInstance().getCurrentUser();
		root = new BorderPane();
		Scene scene = new Scene(root,1200,600);
		this.setScene(scene);
		
		menuBar = new MenuBar();
		
		Menu menu1 = new Menu("Menu");
		MenuItem mItem = new MenuItem("Menu");
		mItem.setOnAction(e ->{
			showMenuList();
		});
		menu1.getItems().addAll(mItem);

		Menu menu2 = new Menu("My Orders");
		MenuItem moItem = new Menu("My Orders");
		moItem.setOnAction(e -> {
			showCustomerOrderList();
		});
		menu2.getItems().addAll(moItem);

		Menu menu3 = new Menu("My Profile");
		MenuItem pItem = new Menu("My Profile");
		pItem.setOnAction(e -> {
			showCustomerProfile();
		});
		menu3.getItems().addAll(pItem);
			
		
		menuBar.getMenus().addAll(menu1);
		menuBar.getMenus().addAll(menu2);
		menuBar.getMenus().addAll(menu3);
		root.setTop(menuBar);
		menuBar.setPadding(new Insets(10));
		
		
		
		contentArea = new VBox(40);
		contentArea.setPadding(new Insets(10));
		contentArea.setAlignment(Pos.CENTER);
//		contentArea.getChildren().addAll(lb);
		root.setCenter(contentArea);
		
	}
	
	private void loadMenuItemsData(TableView<FoodItem> menuItemsTable) {
		ArrayList<FoodItem> menuItemList = menuController.showMenuItems();
		menuItemsTable.getItems().setAll(menuItemList);
	}
	
	private void showMenuList() {
		contentArea.getChildren().clear();
	
		Label titleLabel = new Label("Menu Items");
		contentArea.getChildren().addAll(titleLabel);
		
		TableView<FoodItem> menuItemsTable = createMenuItemsTable();
		TableView<FoodItem> cartItemsTable = createCartItemsTable(orderCart, orderQuantity);
		loadMenuItemsData(menuItemsTable);
		menuItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		cartItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		contentArea.getChildren().addAll(menuItemsTable);
		contentArea.getChildren().addAll(cartItemsTable);
		
		if(!cartItemsTable.getItems().isEmpty()) {
			submitOrder();
		}
	}
	
	private TableView<FoodItem> createMenuItemsTable(){
		TableView<FoodItem> menuItemsTable = new TableView<>();
		
		TableColumn<FoodItem, String> idColumn = new TableColumn<>("Menu ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemID"));	
		
		TableColumn<FoodItem, String> nameColumn = new TableColumn<>("Menu Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemName"));
		
		TableColumn<FoodItem, String> descriptionColumn = new TableColumn<>("Menu Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemDescription"));
		
		TableColumn<FoodItem, Integer> priceColumn = new TableColumn<>("Menu Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemPrice"));
		
		TableColumn<FoodItem, Void> actionColumn = new TableColumn<>("Add Order");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private Button addButton = new Button("Add");
			
			{
				addButton.setOnAction(e -> {
//					setGraphic(quantityInput);
					FoodItem curr = getTableView().getItems().get(getIndex());
					QuantityInput qi = new QuantityInput(curr);
					qi.showAndWait();
					
					if(qi.isBtnPressed()) {
						orderCart.add(curr);
						orderQuantity.add(qi.getQty());
						reloadPage();
					}
					
				});
			}
			
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				
				if(empty || getIndex() < 0 || getIndex() > menuItemsTable.getItems().size()) {
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
	
	private TableView<FoodItem> createCartItemsTable(ArrayList<FoodItem> foodList, ArrayList<Integer> orderQuantity) {
	    TableView<FoodItem> menuItemsTable = new TableView<>();

	    TableColumn<FoodItem, String> idColumn = new TableColumn<>("Menu ID");
	    idColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemID"));

	    TableColumn<FoodItem, String> nameColumn = new TableColumn<>("Menu Name");
	    nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));

	    TableColumn<FoodItem, String> descriptionColumn = new TableColumn<>("Menu Description");
	    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription"));

	    TableColumn<FoodItem, Integer> priceColumn = new TableColumn<>("Menu Price");
	    priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));

	    TableColumn<FoodItem, Integer> qtyColumn = new TableColumn<>("Quantity");
	    qtyColumn.setCellValueFactory(cellData -> {
            int index = foodList.indexOf(cellData.getValue());
            return index >= 0 ? new SimpleIntegerProperty(orderQuantity.get(index)).asObject() : null;
        });
	    
	    TableColumn<FoodItem, Void> actionColumn = new TableColumn<>("Add Order");
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
				
				if(empty || getIndex() < 0 || getIndex() > menuItemsTable.getItems().size()) {
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
	
	private void loadOrderListData(TableView<Order> orderListTable) {
		ArrayList<Order> orderList = orderController.showOrderList();
		orderListTable.getItems().setAll(orderList);
	}
	
	private void showCustomerOrderList() {
		contentArea.getChildren().clear();
		
		Label titleLabel = new Label("Order List");
		contentArea.getChildren().addAll(titleLabel);
		
		TableView<Order> orderListTable = createOrderListTable();
		loadOrderListData(orderListTable);
		orderListTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		contentArea.getChildren().addAll(orderListTable);
	}
	
	private TableView<Order> createOrderListTable(){
		TableView<Order> orderListTable = new TableView<>();
		
		TableColumn<Order, String> orderIDColumn = new TableColumn<>("Order ID");
		orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
		
		TableColumn<Order, String> userIDColumn = new TableColumn<>("User ID");
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));
		
		TableColumn<Order, String> paymentTypeColumn = new TableColumn<>("Payment Type");
		paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("PaymentType"));
		
		TableColumn<Order, Integer> paymentAmountColumn = new TableColumn<>("Payment Amount");
		paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("PaymentAmount"));
		
		TableColumn<Order, String> statusColumn = new TableColumn<>("Payment Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("PaymentStatus"));
		
		TableColumn<Order, Void> actionColumn = new TableColumn<>("Order Details");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private Button linkButton = new Button("See Details");
			private HBox container = new HBox(10);
			
			{
				linkButton.setOnAction(e -> {
					Order o = getTableView().getItems().get(getIndex());
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
	
	private void loadOrderDetailsData(TableView<OrderDetails> orderDetailsTable, Order order) {
		ArrayList<OrderDetails> orderDetails = orderController.showOrderDetailsByOrder(order);
		orderDetailsTable.getItems().setAll(orderDetails);
	}
	
	private void showOrderDetails(Order order) {
		contentArea.getChildren().clear();
		
		Label titleLabel = new Label("Order Details");
		contentArea.getChildren().addAll(titleLabel);
		
		TableView<OrderDetails> orderDetailsTable = createOrderDetailsTable(order);
		loadOrderDetailsData(orderDetailsTable, order);
		orderDetailsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		contentArea.getChildren().addAll(orderDetailsTable);
		
		Button backBtn = new Button("<< Back");
		contentArea.getChildren().addAll(backBtn);
		backBtn.setOnAction(e-> {
			showCustomerOrderList();
		});
	}
	
	private TableView<OrderDetails> createOrderDetailsTable(Order order){
		TableView<OrderDetails> orderDetailsTable = new TableView<>();
		
		TableColumn<OrderDetails, String> orderDetailsIDColumn = new TableColumn<>("ID");
		orderDetailsIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		
		TableColumn<OrderDetails, String> orderIDColumn = new TableColumn<>("Order ID");
		orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("Order ID"));
		
		TableColumn<OrderDetails, String> menuIDColumn = new TableColumn<>("Menu ID");
		menuIDColumn.setCellValueFactory(new PropertyValueFactory<>("Menu ID"));
		
		TableColumn<OrderDetails, Integer> qtyColumn = new TableColumn<>("Quantity");
		qtyColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		
		TableColumn<OrderDetails, Void> actionColumn = new TableColumn<>("Update Data");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private Button editButton = new Button("Edit");
			private HBox container = new HBox(10);
			
			{
				editButton.setOnAction(e -> {
					OrderDetails od = getTableView().getItems().get(getIndex());
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
	
	private void updateOrderDetails(Order order, OrderDetails od) {
		contentArea.getChildren().clear();
		
		HBox container = new HBox(10);
		Label prompt = new Label ("Please Input New Quantity :");
		TextField qtyInput = new TextField();
		container.getChildren().addAll(prompt, qtyInput);
		
		Button updateBtn = new Button("Update Data");
		updateBtn.setOnAction(e -> {
			od.setQuantity(Integer.parseInt(qtyInput.getText()));
			orderController.updateOrderDetails(od, Integer.parseInt(qtyInput.getText()));
			showOrderDetails(order);
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