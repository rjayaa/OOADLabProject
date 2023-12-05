package Controller.Order;
import java.util.ArrayList;

import DBConnection.Singleton;
import Model.MenuItem.MenuItem;
import Model.Order.Order;
import Model.OrderItem.OrderItem;
import Model.User.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OrderInformationInput extends Stage{
	private Scene scene;
	private BorderPane root;
	private VBox contentArea;
	
	// Controller
//	private static UserController usercontroller = new UserController();
//	private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);

	// Component
	private Label lblAddress, lblPayment;
	private TextField txtAddress;
	private Button btnConfirm, btnCancel;
	
	OrderController orderController = new OrderController();
	
	public OrderInformationInput(ArrayList<MenuItem> orderCart, ArrayList<Integer> orderQuantity, User currentUser) {
		this.setTitle("Order Information");
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 400, 250);
		this.setScene(scene);

		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));

		lblAddress = new Label("Your ID :");
		txtAddress = new TextField(String.valueOf(currentUser.getUserId())); //Masukin ID User
		txtAddress.setDisable(true);

		lblPayment = new Label("Input Payment Type :");
		ToggleGroup paymentType = new ToggleGroup();
		
		RadioButton cashBtn = new RadioButton("Cash");
		cashBtn.setToggleGroup(paymentType);
		
		RadioButton transferBtn = new RadioButton("Bank Transfer");
		transferBtn.setToggleGroup(paymentType);

		btnConfirm = new Button("Confirm");
		btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
	
			}
		});
		
		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e->this.close());

		TableView<MenuItem> cartItemsTable = createCartItemsTable(orderCart, orderQuantity);
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnConfirm,btnCancel);
		buttonBox.setAlignment(Pos.CENTER);
		contentArea.getChildren().addAll(lblAddress, txtAddress, lblPayment, cashBtn, transferBtn, cartItemsTable, buttonBox);
		root.setCenter(contentArea);
		
		
	}
	
	private int calculateTotalPayment(ArrayList<MenuItem> orderCart, ArrayList<Integer> orderQuantity) {
		
		int totalPayment = 0;
		
		for(int i = 0; i < orderCart.size(); i++) {
			totalPayment += orderCart.get(i).getMenuItemPrice() * orderQuantity.get(i);
		}
		
		return totalPayment;
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

	    menuItemsTable.getColumns().add(nameColumn);
	    menuItemsTable.getColumns().add(descriptionColumn);
	    menuItemsTable.getColumns().add(priceColumn);
	    menuItemsTable.getColumns().add(qtyColumn);

	    menuItemsTable.getItems().addAll(foodList);

	    return menuItemsTable;
	}
	
}
