package view.Admin;

import controller.adminController.AdminController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdminView {
    private HBox hbox;
    private Stage stage;
    private AdminController adminController;

    public AdminView(Stage stage, AdminController adminController) {
        this.stage = stage;
        this.adminController = adminController;
        hbox = new HBox(5);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER);

        Button viewUserButton = new Button("View User");
        viewUserButton.setMinWidth(100);
        viewUserButton.setMinHeight(50);
        viewUserButton.setOnAction(e -> {
            Stage adminViewUserStage = new Stage();
            AdminViewUser adminViewUser = new AdminViewUser(adminViewUserStage, adminController);
            adminViewUser.show(adminViewUserStage);
        });

        Button viewMenuButton = new Button("View Menu");
        viewMenuButton.setMinWidth(100);
        viewMenuButton.setMinHeight(50);
        viewMenuButton.setOnAction(e -> {
            Stage adminViewMenuStage = new Stage();
            AdminViewMenu adminViewMenu = new AdminViewMenu(adminViewMenuStage, adminController);
            adminViewMenu.show(adminViewMenuStage);
        });

        hbox.getChildren().addAll(viewUserButton, viewMenuButton);
    }

    public void show(Stage stage) {
        Scene scene = new Scene(hbox, 400, 400);
        stage.setTitle("Admin View");
        stage.setScene(scene);
        stage.show();
    }
}