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

public class CustomerMenuPage extends Stage {
	private BorderPane root;
	private Scene scene;
	private Button btnOrder,btnViewCart,btnExit;
	private Label lblWelcome;
	private User currentUser;
	
	public CustomerMenuPage() {
		super(StageStyle.DECORATED);
		// get user session
		this.currentUser = Singleton.getInstance().getCurrentUser();
		root = new BorderPane();
		scene = new Scene(root,600,400);
		this.setTitle("Mystic Grills");
		this.setScene(scene);
		
		
		btnOrder = new Button("Order Menu");
		btnViewCart = new Button("View My Cart");
		btnExit = new Button("Exit");
		
		lblWelcome = new Label("Welcome Mystic Grills!");
		lblWelcome.setFont(Font.font("Arial", FontWeight.BOLD,20));
		
		btnOrder.setPrefSize(100, 50);
		btnViewCart.setPrefSize(100, 50);
		btnExit.setPrefSize(100, 50);
		
		btnOrder.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				// move to menucart
				CustomerPage cp = new CustomerPage();
				cp.show();
				Stage stg = (Stage) btnOrder.getScene().getWindow();
				stg.close();
			}
		});
		
		HBox btnHbox1 = new HBox(10);
		btnHbox1.getChildren().addAll(btnOrder, btnViewCart);
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
