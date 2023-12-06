package View.Customer;

import DBConnection.Singleton;
import Model.User.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerLandingPage extends Stage {
	private BorderPane root;
	private Scene scene;
	private Button btnOrder,btnViewOrder,btnExit;
	private Label lblWelcome;
	
	
	public CustomerLandingPage() {
		super(StageStyle.DECORATED);
		// get user session
		
		root = new BorderPane();
		scene = new Scene(root,600,400);
		this.setTitle("Mystic Grills");
		this.setScene(scene);
		
		
		btnOrder = new Button("Order Menu");
		btnViewOrder = new Button("View My Order");
		btnExit = new Button("Exit");
		
		lblWelcome = new Label("Welcome Mystic Grills!");
		lblWelcome.setFont(Font.font("Arial", FontWeight.BOLD,20));
		
		btnOrder.setPrefSize(100, 50);
		btnViewOrder.setPrefSize(100, 50);
		btnExit.setPrefSize(100, 50);
		
		btnOrder.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				// move to menucart
				CustomerMenuPage cp = new CustomerMenuPage();
				cp.show();
				Stage stg = (Stage) btnOrder.getScene().getWindow();
				stg.close();
			}
		});
		
		btnViewOrder.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				CustomerOrderStatusPage cosp = new CustomerOrderStatusPage();
				cosp.show();
				Stage stg = (Stage) btnOrder.getScene().getWindow();
				stg.close();
			}
		});
		
		btnExit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				close();
			}
		});
		
		
		HBox btnHbox1 = new HBox(10);
		btnHbox1.getChildren().addAll(btnOrder, btnViewOrder);
		btnHbox1.setAlignment(Pos.CENTER);

		HBox btnHbox2 = new HBox(10);
		btnHbox2.getChildren().add(btnExit);
		btnHbox2.setAlignment(Pos.CENTER);

		VBox vboxButtons = new VBox(10);
		vboxButtons.getChildren().addAll(lblWelcome,btnHbox1, btnHbox2);
		vboxButtons.setAlignment(Pos.CENTER);

	
		root.setCenter(vboxButtons);
		
		
	}
}
