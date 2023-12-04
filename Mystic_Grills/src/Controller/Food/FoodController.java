package Controller.Food;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBConnection.Singleton;
import Model.FoodItem.FoodItem;

public class FoodController {

	public ArrayList<FoodItem> showMenuItems() {
		ArrayList<FoodItem> foodItem = new ArrayList<>();
		String query = "SELECT * FROM fooditem";

		try(Connection connection = Singleton.getInstance().getConnection()){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				String id = resultSet.getString("menuItemID");
				String name = resultSet.getString("menuItemName");
				String desc = resultSet.getString("menuItemDescription");
				int price = resultSet.getInt("menuItemPrice");
				foodItem.add(new FoodItem(id,name,desc,price));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return foodItem;
	}
	
	

}
