package Controller.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBConnection.Singleton;
import Model.MenuItem.MenuItem;

public class MenuController {

	private static Connection connection = Singleton.getInstance().getConnection();
	public ArrayList<MenuItem> showMenuItems() {
		ArrayList<MenuItem> foodItem = new ArrayList<>();
		String query = "SELECT * FROM menuItem";

		try{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				int id = resultSet.getInt("menuItemID");
				String name = resultSet.getString("menuItemName");
				String desc = resultSet.getString("menuItemDescription");
				int price = resultSet.getInt("menuItemPrice");
				foodItem.add(new MenuItem(id,name,desc,price));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return foodItem;
	}
	
	
	public int getLastId() {
		int lastId = 1;
		String query = "SELECT orderId FROM orderitem ORDER BY orderId DESC LIMIT 1";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				lastId = rs.getInt("orderId");
		} catch (SQLException e) {
		}
		return lastId;
	}
	

}
