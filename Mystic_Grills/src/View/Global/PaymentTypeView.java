package View.Global;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Controller.Order.OrderController;
import DBConnection.Singleton;
import Model.Order.Order;
import Model.OrderItem.OrderItem;
import Model.User.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PaymentTypeView extends Stage {
	
	// framework
	private Scene scene;
	private BorderPane root;
	private VBox contentArea;
	
	
	// Component
	private Label lblPayment;
	private Button btnConfirm, btnCancel;
	
	// Controller
	OrderController orderController = new OrderController();
	
	int currUserId = Singleton.getInstance().getCurrentUser().getUserId();
	// Object
	private User currentUser;
	private Date now = new Date();
	private Timestamp timeStampNow = new Timestamp(now.getTime());
	private ArrayList<Order> order = new ArrayList<>();
	public PaymentTypeView(ArrayList<OrderItem> orderList) {
		
		this.setTitle("Order Information");
		this.initStyle(StageStyle.DECORATED);
		
		root = new BorderPane();
		scene = new Scene(root, 300, 100);
		this.setScene(scene);
		
		
		contentArea = new VBox(5);
		contentArea.setPadding(new Insets(5));

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
		        
		        RadioButton selectedRadioButton = (RadioButton) paymentType.getSelectedToggle();
		        if (selectedRadioButton != null) {
		        	
		            orderController.insertOrderItem(orderList);
		            for(OrderItem ord : orderList) {
		            	Order newOrder = new Order(ord.getOrderId(),
		            			currUserId,orderController.getMenuItemById(ord.getMenuItemId()),
		            			"Pending",timeStampNow,ord.getQuantity());
		            	order.add(newOrder);
		            }
		            
		            orderController.insertOrder(order);
		            close();
		        }
		    }
		});

		
		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e->this.close());
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(btnConfirm,btnCancel);
		buttonBox.setAlignment(Pos.CENTER);
		contentArea.getChildren().addAll( lblPayment, cashBtn, transferBtn, buttonBox);
		root.setCenter(contentArea);
	}
}
