package View.Chef;

import DBConnection.Singleton;
import Model.User.User;
import View.Admin.AdminViewAllUserPage;
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

public class ChefLandingPage extends Stage {
	private BorderPane root;
	private Scene scene;
	private Button btnViewOrder,btnExit;
	private Label lblCashier;
	
	
	public ChefLandingPage() {
		super(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 600, 400);
		this.setTitle("Mystic Grills");
		this.setScene(scene);
		VBox vb = new VBox(20);
		HBox hb = new HBox(20);

		lblCashier = new Label("Chef View");
		lblCashier.setFont(Font.font("Arial", FontWeight.BOLD,20));
		btnViewOrder = new Button("View Order");
		btnExit = new Button("Exit");
		
		btnViewOrder.setPrefSize(150, 50);
		btnExit.setPrefSize(100, 50);
		
		
		btnViewOrder.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ChefOrderPage cop = new ChefOrderPage();
				cop.show();
				close();
			}
		});
		
		btnExit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				close();
			}
		});
		
		
		hb.getChildren().addAll(btnViewOrder);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(lblCashier, hb, btnExit);
		vb.setAlignment(Pos.CENTER);
		root.setCenter(vb);

	}

}
