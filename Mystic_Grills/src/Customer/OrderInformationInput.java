package Customer;

import java.util.ArrayList;

import Controller.Order.OrderController;
import Model.FoodItem.FoodItem;
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
	
	public OrderInformationInput(ArrayList<FoodItem> orderCart, ArrayList<Integer> orderQuantity) {
		this.setTitle("Order Information");
		this.initStyle(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 400, 250);
		this.setScene(scene);

		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));

		lblAddress = new Label("Your ID :");
		txtAddress = new TextField("US001"); //Masukin ID User
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

		TableView<FoodItem> cartItemsTable = createCartItemsTable(orderCart, orderQuantity);
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnConfirm,btnCancel);
		buttonBox.setAlignment(Pos.CENTER);
		contentArea.getChildren().addAll(lblAddress, txtAddress, lblPayment, cashBtn, transferBtn, cartItemsTable, buttonBox);
		root.setCenter(contentArea);
		
		
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

	    menuItemsTable.getColumns().add(nameColumn);
	    menuItemsTable.getColumns().add(descriptionColumn);
	    menuItemsTable.getColumns().add(priceColumn);
	    menuItemsTable.getColumns().add(qtyColumn);

	    menuItemsTable.getItems().addAll(foodList);

	    return menuItemsTable;
	}
	
}
