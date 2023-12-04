package View.Customer;

import DBConnection.Singleton;
import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerView extends Stage {
	private User currentUser;
	private BorderPane root;
	private Scene scene;
	private MenuBar menuBar;
	private Button btnOrder, btnViewCart, btnExit;
	private double btnWidth = 100;
	private double btnHeight = 30;

	public CustomerView() {

		// TODO Auto-generated constructor stub
		super(StageStyle.DECORATED);

		this.currentUser = Singleton.getInstance().getCurrentUser();

		root = new BorderPane();
		scene = new Scene(root, 800, 600);
		this.setTitle("Mystic Grills");
		this.setScene(scene);

		menuBar = new MenuBar();

		Menu menu = new Menu("Home");

		btnOrder = new Button("Order");
		btnViewCart = new Button("View Cart");
		btnExit = new Button("Exit");

		btnOrder.setPrefSize(btnWidth, btnHeight);
		btnViewCart.setPrefSize(btnWidth, btnHeight);
		btnExit.setPrefSize(btnWidth, btnHeight);

		btnOrder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				OrderView ov = new OrderView();
//				ov.show();
//					Stage stg = (Stage) btnOrder.getScene().getWindow();
//					stg.close();
			}
		});

		HBox btnHbox1 = new HBox(10);
		btnHbox1.getChildren().addAll(btnOrder, btnViewCart);
		btnHbox1.setAlignment(Pos.CENTER);

		HBox btnHbox2 = new HBox(10);
		btnHbox2.getChildren().add(btnExit);
		btnHbox2.setAlignment(Pos.CENTER);

		VBox vboxButtons = new VBox(10);
		vboxButtons.getChildren().addAll(btnHbox1, btnHbox2);
		vboxButtons.setAlignment(Pos.CENTER);

		menuBar.getMenus().addAll(menu);
		menuBar.setPadding(new Insets(10));
		root.setTop(menuBar);
		root.setCenter(vboxButtons);
	}

}