package Main;

import Admin.ControlPanel;
import Cashier.CashierControl;
import Customer.CustomerPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	BorderPane root = new BorderPane();
	Scene scene = new Scene(root, 1200,600);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void contentScreen() {
		//validasi role 
		CustomerPage cp = new CustomerPage();
		cp.show();
		
		
		//new CashierControl();
		//new ControlPanel();
//		root = new BorderPane();
//		scene = new Scene(root, 1200,600);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		initScene();
		contentScreen();

	}
	

}
