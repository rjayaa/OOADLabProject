package View.Chef;

import java.sql.Timestamp;
import java.util.ArrayList;

import Controller.Order.OrderController;
import Model.Order.Order;
import View.Cashier.CashierLandingPage;
import View.Cashier.CashierOrderPage;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class ChefOrderPage extends Stage {
	private BorderPane root;
	private Scene scene;
	
	// controller
	OrderController orderController = new OrderController();
	// component
	private Label lblOrder;
	private Button btnBackToMenu;
	
	public ChefOrderPage() {
		super(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 800, 600);
		this.setTitle("Mystic Grills");
		this.setScene(scene);
		VBox vb = new VBox(40);
		
		
		lblOrder = new Label("Order Status!");
		lblOrder.setFont(Font.font("Arial", FontWeight.BOLD,20));
		TableView<Order> menuCartTable = createOrderTable();
		loadMenuItemsData(menuCartTable);
		menuCartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		btnBackToMenu = new Button("Back to Menu");
		btnBackToMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				ChefLandingPage clp = new ChefLandingPage();
				clp.show();
				Stage currentStage = (Stage) btnBackToMenu.getScene().getWindow(); 
				currentStage.close();
			}
		});
		vb.getChildren().addAll(lblOrder,menuCartTable, btnBackToMenu);
		vb.setAlignment(Pos.CENTER);
		root.setTop(vb);
		
	}
	
	
	
	private  TableView<Order> createOrderTable(){
		TableView<Order> orderTable = new TableView<>();
		TableColumn<Order, String> userName = new TableColumn<>("Username");
		userName.setCellValueFactory(data->{
			int userId = data.getValue().getOrderUser();
			String username = orderController.getUserById(userId);
			
			return new SimpleStringProperty(username);
		});
		TableColumn<Order, String> orderItems = new TableColumn<>("Menu Items");
		orderItems.setCellValueFactory(new PropertyValueFactory("orderItems"));
		
		TableColumn<Order, String> orderStatus = new TableColumn<>("Order Status");
		orderStatus.setCellValueFactory(new PropertyValueFactory("orderStatus"));
		
		TableColumn<Order, Timestamp> orderDate = new TableColumn<>("Order Date");
		orderDate.setCellValueFactory(new PropertyValueFactory("orderDate"));
		
		TableColumn<Order, Integer> orderTotal = new TableColumn<>("Order Total");
		orderTotal.setCellValueFactory(new PropertyValueFactory("orderTotal"));
		
		TableColumn<Order, Void> actionColumn = new TableColumn<>("Action Column");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private final Button updateButton = new Button("Update Order Status");
			private final Button deleteButton = new Button("Remove Order");

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
					setOnMouseClicked(null);
				} else {
					updateButton.setOnAction(event -> {
						Order curr = getTableView().getItems().get(getIndex());
						orderController.updateOrderStatusByChef(curr.getOrderId(), curr.getOrderItems());
						reloadPage();
					});
					
					deleteButton.setOnAction(event -> {
						Order curr = getTableView().getItems().get(getIndex());
						orderController.deleteOrder(curr.getOrderId(), curr.getOrderItems());
						reloadPage();
					});
					
					Order curr = getTableView().getItems().get(getIndex());
					
					if(curr.getOrderStatus().equals("Pending")) {
						setGraphic(deleteButton);
					}
					else if(curr.getOrderStatus().equals("Paid")) {
						setGraphic(updateButton);
					}
					else {
						setGraphic(null);
					}
				}
			}
		});
		
		orderTable.getColumns().add(userName);
		orderTable.getColumns().add(orderItems);
		orderTable.getColumns().add(orderStatus);
		orderTable.getColumns().add(orderDate);
		orderTable.getColumns().add(orderTotal);
		orderTable.getColumns().add(actionColumn);
		
		return orderTable;
	}
	
	private void loadMenuItemsData(TableView<Order> menuOrderTable) {
		ArrayList<Order> showOrderList = orderController.showOrderListForChef();
		menuOrderTable.getItems().setAll(showOrderList);
	}

	private void reloadPage() {
	    Platform.runLater(() -> {
	    	ChefOrderPage cop = new ChefOrderPage();
			cop.show();
			close();
	    });
	}
}
