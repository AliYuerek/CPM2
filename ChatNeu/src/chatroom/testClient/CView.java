package chatroom.testClient;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CView {
	
	private CModel model;
	protected Stage stage;
	
	//ChatScene
	protected Label lblIpAddress;
	protected TextField txtIpAddress;
	protected Label lblPort;
	protected TextField txtPort, txtUser2;
	protected Label lblYoN, lblUser, lblPw, lblUser2, lblPw2, lblCC, chatName, l, label24, label29, label36;
	protected SimpleStringProperty newestMessage2 = new SimpleStringProperty("false");
	protected TextField txtUser, txtPw, lblToken, lblCCField, labelOne, labelTwo, labelThree;
	protected Button btnConnect, logout,  btnCC, btnJoin, btnCCCreate, btnE, btnBack, btnWeiter3, btnZurück, btnZurück2;
	protected Scene scene2;
	protected PasswordField txtPw2;
	protected TextArea txtChatArea;
	protected TextField txtChatMessage;
	protected Button btnSend, btnCreate, btnWeiter, btnC, btnL, btnWeiter2, btnCreateChat, btnLogin;
	protected Scene scene3;
	protected Scene scene1;
	protected Scene scene4;
	protected Scene scene5;
	protected Scene scene6;
	protected Scene scene7;
	protected ToggleGroup tg;
	protected Button btnAddNewElt = new Button("Add New Element");
	protected Label lblResult = new Label();
	protected ListView<String> listView;
	protected MenuM menu;
	protected RadioButton Englisch, Deutsch;
	protected ToggleGroup group;
	
	public CView(CModel model, Stage stage) {
		this.model = model;
		this.stage = stage;
		
		scene1 = new Scene(connectPane(), 500, 500);
		scene2 = new Scene(createLogin(), 500, 500);
		scene3 = new Scene(chatPane(), 800, 600);
		scene4 = new Scene(clPane(), 500, 500);
		scene5 = new Scene(logPane(), 500, 500);
		scene6 = new Scene(chatListPane(), 700, 600);
		scene7 = new Scene(ccPane(), 500, 500);
		scene1.getStylesheets().add(getClass().getResource("C.css").toExternalForm());
		scene2.getStylesheets().add(getClass().getResource("C.css").toExternalForm());
		scene3.getStylesheets().add(getClass().getResource("C.css").toExternalForm());
		scene4.getStylesheets().add(getClass().getResource("C.css").toExternalForm());
		scene5.getStylesheets().add(getClass().getResource("C.css").toExternalForm());
		scene6.getStylesheets().add(getClass().getResource("C.css").toExternalForm());
		scene7.getStylesheets().add(getClass().getResource("C.css").toExternalForm());
		
		stage.setScene(scene1);
		stage.setTitle("FHNW-Chat");	
		stage.centerOnScreen();	
	}

	protected void start() {
		stage.show();		
		}

	protected void stop() {
		stage.hide();		
		}
	
	public Pane ccPane() {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER);
		lblCC = new Label("Name of the Chat:");
		lblCCField = new TextField();
		btnCCCreate = new Button("Create Chat");
		btnBack = new Button("Back to Chat-list");
		labelThree = new TextField("");
		labelThree.setDisable(true);
		labelThree.setMinSize(300, 20);	
		pane.add(lblCC, 0, 0);
		pane.add(lblCCField, 1, 0);
		pane.add(labelThree, 0, 3, 2, 3);
		pane.add(btnBack, 0, 2);
		pane.add(btnCCCreate, 1, 2);
		pane.getStyleClass().add("scene");	
		return pane;
	}
	
	public Pane chatListPane() {
		logout = new Button("Logout");
		logout.getStyleClass().add("b");
		btnCreateChat = new Button("Refresh Chats");
		btnCC = new Button("Create new Chat");
		btnJoin = new Button("Sign up for Chat");
		btnWeiter3 = new Button("Enter Chat");
		btnWeiter3.setDisable(true);
		label29 = new Label("Select one of the existing Chats and sign up for the Chat (see below):");
		listView = new ListView<>();
		HBox.setHgrow(lblResult, Priority.ALWAYS); 
		HBox hbox = new HBox(lblResult);	
		HBox.setHgrow(lblResult, Priority.ALWAYS); 
		HBox hbox2 = new HBox(lblResult);
		VBox root = new VBox();
		root.getStyleClass().add("root"); 
		VBox.setVgrow(listView, Priority.ALWAYS); 
		root.getChildren().addAll(logout, btnCC, hbox2, label29, listView, btnCreateChat, hbox, btnJoin, btnWeiter3);

		btnCreateChat.setMaxWidth(Double.MAX_VALUE); 
		btnJoin.setMaxWidth(Double.MAX_VALUE);
		btnWeiter3.setMaxWidth(Double.MAX_VALUE);
		root.getStyleClass().add("scene");
		return root;
	}
	
	public Pane logPane() {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER);
		lblUser2 = new Label("Username:");
		lblPw2 = new Label("Password:");
		txtUser2 = new TextField();
		txtPw2 = new PasswordField();
		btnLogin = new Button("Login");
		btnWeiter2 = new Button("Continue");
		btnWeiter2.setDisable(true);
		btnZurück = new Button("Go back");
		labelTwo = new TextField("");
		labelTwo.setDisable(true);
		labelTwo.setMinSize(300, 20);		
		pane.add(labelTwo, 0, 5, 2, 5);
		pane.add(btnZurück, 0, 0);
		pane.add(lblUser2, 0, 1);
		pane.add(lblPw2, 0, 2);
		pane.add(txtUser2, 1, 1);
		pane.add(txtPw2, 1, 2);
		pane.add(btnLogin, 0, 4);
		pane.add(btnWeiter2, 1, 4);
		pane.getStyleClass().add("scene");	
		return pane;
	}
	
	public Pane clPane() {		
		VBox box = new VBox();
		l = new Label();
		l.setText("Welcome to the FHNW-Chat!");
		l.setStyle("-fx-font-family: Impact, Charcoal, sans-serif;"
				+ "-fx-font-size: 20px;"
				+ "-fx-letter-spacing: 0px;"
				+ "-fx-word-spacing: 0px;"
				+ "-fx-color: #000000;"
				+ "-fx-font-weight: bold;"
				+ "-fx-text-decoration: underline;"
				+ "-fx-font-style: normal;"
				+ "-fx-font-variant: normal;"
				+ "-fx-text-transform: none;");
		btnC = new Button("Create a new User");
		btnL = new Button("Login");
		btnE = new Button("Exit the App");
		btnE.getStyleClass().add("b");
		HBox box2 = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(10);
		group = new ToggleGroup();
		Englisch = new RadioButton("Englisch");
	    Englisch.setToggleGroup(group);
	    Englisch.setSelected(true);
	 
	    
	    Deutsch = new RadioButton("Deutsch");
	    Deutsch.setToggleGroup(group);
	    label24 = new Label("Language: ");
	       
		box2.getChildren().add(btnE);
		box.getChildren().addAll(l, btnC, btnL, label24, Englisch, Deutsch);
		BorderPane pane = new BorderPane();
		pane.setTop(box2);
		pane.setCenter(box);
		pane.getStyleClass().add("scene");
		return pane;
	}
	
	public Pane createLogin() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER);
		lblUser = new Label("Username:");
		lblPw = new Label("Password:");
		txtUser = new TextField();
		txtPw = new TextField();
		btnCreate = new Button("Create user");
		btnWeiter = new Button("Continue");
		btnWeiter.setDisable(true);
		btnZurück2 = new Button("Go back");
		labelOne = new TextField("");
		labelOne.setDisable(true);
		labelOne.setMinSize(300, 20);		
		pane.add(lblUser, 0, 0);
		pane.add(lblPw, 0, 1);
		pane.add(txtUser, 1, 0);
		pane.add(txtPw, 1, 1);
		pane.add(labelOne, 0, 4, 3, 4);
		pane.add(btnZurück2, 0, 3);
		pane.add(btnCreate, 1, 3);
		pane.add(btnWeiter, 2, 3);
		pane.getStyleClass().add("scene");
		return pane;
	}
	
	public Pane connectPane() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		lblIpAddress = new Label("IP-address:");
		lblPort = new Label("Port:");
		txtIpAddress = new TextField();
		txtPort = new TextField();
		btnConnect = new Button("Connect");
    
		pane.add(lblIpAddress, 0, 0);
		pane.add(lblPort, 0, 1);
		pane.add(txtIpAddress, 1, 0);
		pane.add(txtPort, 1, 1);
		pane.add(btnConnect, 0, 5);	
		pane.getStyleClass().add("scene");
		return pane;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Pane chatPane() {
		txtChatArea = new TextArea();
		txtChatArea.setEditable(false);
		label36 = new Label("Chat-name: ");
		chatName = new Label("");
		txtChatMessage = new TextField();
		btnSend = new Button("Send");	
		btnSend.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);	
		HBox bottomBox = new HBox(txtChatMessage, btnSend);
		HBox.setHgrow(txtChatMessage, Priority.ALWAYS);	
		menu = new MenuM();
		BorderPane root = new BorderPane();		
		VBox box = new VBox();
		box.getChildren().addAll(label36, chatName);
		box.setSpacing(2);
		root.setBottom(bottomBox);
		root.setRight(box);
		root.setCenter(txtChatArea);
		root.setTop(menu);
		root.getStyleClass().add("scene");
		return root;
	}

}
