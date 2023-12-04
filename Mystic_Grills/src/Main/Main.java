package Main;

import Customer.CustomerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	BorderPane root = new BorderPane();
	Scene scene;

	public static void main(String[] args) {
		 launch(args);
	}

	public void initScene() {
//		LoginView lg = new LoginView();
		CustomerView lg = new CustomerView();
//		CustomerPage lg = new CustomerPage();
		lg.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 initScene();

	}

}
