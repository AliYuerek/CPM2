package chatroom.testClient;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuM extends MenuBar{

	public Menu menuM = new Menu("Menu");
	public Menu exit = new Menu("Exit chat");
	public Menu logout = new Menu("Logout");
	
	
	public MenuM() {
		super();
		menuM.getItems().addAll(exit, logout);
		this.getMenus().addAll(menuM);
		this.setId("menuM");
		
	}
}
