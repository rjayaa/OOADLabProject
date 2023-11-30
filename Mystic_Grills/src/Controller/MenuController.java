package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBConnection.Singleton;
import Model.FoodItem;

public class MenuController {

	public ArrayList<FoodItem> showMenuItems() {
		ArrayList<FoodItem> foodItem = new ArrayList<>();
		String query = "SELECT * FROM menu";

		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				String id = resultSet.getString("MenuItemID");
				String name = resultSet.getString("MenuItemName");
				String desc = resultSet.getString("MenuItemDescription");
				int price = resultSet.getInt("MenuItemPrice");
				foodItem.add(new FoodItem(id,name,desc,price));
			}
//			statement.executeUpdate(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return foodItem;
	}
	
	

}
