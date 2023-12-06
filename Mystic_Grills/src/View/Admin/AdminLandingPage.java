package View.Admin;

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

public class AdminLandingPage extends Stage {
	private BorderPane root;
	private Scene scene;

	// component
	private Label lblAdmin;
	private Button btnViewUser, btnViewMenu, btnExit;

	public AdminLandingPage() {
		super(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 600, 400);
		this.setTitle("Mystic Grills");
		this.setScene(scene);
		VBox vb = new VBox(20);
		HBox hb = new HBox(20);

		lblAdmin = new Label("Admin View");
		lblAdmin.setFont(Font.font("Arial", FontWeight.BOLD,20));
		btnViewUser = new Button("View All User");
		btnViewMenu = new Button("View All Menu");
		btnExit = new Button("Exit");
		
		btnViewUser.setPrefSize(100, 50);
		btnViewMenu.setPrefSize(100, 50);
		btnExit.setPrefSize(100, 50);
		
		
		btnViewUser.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				AdminViewAllUserPage viewUser = new AdminViewAllUserPage();
				viewUser.show();
				close();
			}
		});
		
		btnViewMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnExit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				close();
			}
		});
		
		
		hb.getChildren().addAll(btnViewUser, btnViewMenu);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(lblAdmin, hb, btnExit);
		vb.setAlignment(Pos.CENTER);
		root.setCenter(vb);

	}

}
