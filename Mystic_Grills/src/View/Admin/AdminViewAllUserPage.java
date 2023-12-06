package View.Admin;

import java.util.ArrayList;

import Controller.User.UserController;
import Model.User.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminViewAllUserPage extends Stage {
	private BorderPane root;
	private Scene scene;

	private UserController userController = new UserController();
	// component
	private Label lblViewUser;

	public AdminViewAllUserPage() {
		super(StageStyle.DECORATED);
		root = new BorderPane();
		scene = new Scene(root, 600, 400);
		this.setTitle("Mystic Grills");
		this.setScene(scene);
		VBox vb = new VBox(20);
		HBox hb = new HBox(20);

		lblViewUser = new Label("User List");
		lblViewUser.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		TableView<User> userTable = createUserTable();
		loadMenuItemsData(userTable);
		userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		vb.getChildren().addAll(lblViewUser,userTable);
		vb.setAlignment(Pos.CENTER);
		
		root.setTop(vb);

	}

	private TableView<User> createUserTable() {
		TableView<User> userTable = new TableView<>();
		TableColumn<User, Integer> userId = new TableColumn<>("User ID");
		userId.setCellValueFactory(new PropertyValueFactory("userId"));

		TableColumn<User, String> userRole = new TableColumn<>("User Role");
		userRole.setCellValueFactory(new PropertyValueFactory("userRole"));
		
		TableColumn<User, String> userName = new TableColumn<>("Username");
		userName.setCellValueFactory(new PropertyValueFactory("userName"));

		TableColumn<User, String> userEmail = new TableColumn<>("User Email");
		userEmail.setCellValueFactory(new PropertyValueFactory("userEmail"));

		TableColumn<User, Void> actionColumn = new TableColumn<>("Action Column");
		actionColumn.setCellFactory(e -> new TableCell<>() {
			private final Button btnUpdateUser = new Button("Update User");
			
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item,empty);
				if(empty) {
					setGraphic(null);
					setOnMouseClicked(null);
				}else {
					setGraphic(btnUpdateUser);
					btnUpdateUser.setOnAction(event ->{
						User usr = getTableView().getItems().get(getIndex());
						AdminUpdateUserPage aup = new AdminUpdateUserPage(usr);
						
						aup.showAndWait();
						if(aup.isBtnPressed()) {
							usr.setUserRole(aup.getNewRole().toString());
							getTableView().refresh();
						}
					});
				
				}
			}
		});
		userTable.getColumns().add(userId);
		userTable.getColumns().add(userName);
		userTable.getColumns().add(userEmail);
		userTable.getColumns().add(userRole);
		userTable.getColumns().add(actionColumn);

		return userTable;
	}

	private void loadMenuItemsData(TableView<User> userListTable) {
		ArrayList<User> showUserTable = userController.showUserTable();
		userListTable.getItems().setAll(showUserTable);
	}

}
