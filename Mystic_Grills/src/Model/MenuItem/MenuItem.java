package Model.MenuItem;

public class MenuItem {
	private int menuItemId;
	private String menuItemName;
	private String menuItemDescription;
	private Integer menuItemPrice;
	
	public MenuItem(int menuItemId, String menuItemName, String menuItemDescription, Integer menuItemPrice) {
		
		this.menuItemId = menuItemId;
		this.menuItemName = menuItemName;
		this.menuItemDescription = menuItemDescription;
		this.menuItemPrice = menuItemPrice;
	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemID) {
		this.menuItemId = menuItemID;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public String getMenuItemDescription() {
		return menuItemDescription;
	}

	public void setMenuItemDescription(String menuItemDescription) {
		this.menuItemDescription = menuItemDescription;
	}

	public Integer getMenuItemPrice() {
		return menuItemPrice;
	}

	public void setMenuItemPrice(Integer menuItemPrice) {
		this.menuItemPrice = menuItemPrice;
	}
	
	

}
