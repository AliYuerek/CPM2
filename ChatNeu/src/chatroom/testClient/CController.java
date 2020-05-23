package chatroom.testClient;

import java.util.Iterator;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;

public class CController {
	
	private CModel model;
	private CView view;
	String YoN;
	String value;
	public String language;

	public CController(CModel model, CView view) {
		this.model = model;
		this.view = view;
		
		view.btnConnect.setOnAction( event -> {
			if(!model.found9.getValue() && !view.txtIpAddress.getText().isEmpty() && !view.txtPort.getText().isEmpty() 
				&& model.validateIpAddress(view.txtIpAddress.getText()) && model.validatePortNumber(view.txtPort.getText())) {
				String ipAddress = view.txtIpAddress.getText();
				int port = Integer.parseInt(view.txtPort.getText());
				YoN = "no";
				
				model.connect(ipAddress, port, YoN);
				}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning alert");
				 
		        alert.setContentText("Please enter values!");
		 
		        alert.showAndWait();
		        model.found9.setValue(false);
			}	
			
			});
		model.found9.addListener((observable, oldValue, newValue) ->{
			if(!newValue) {
				//view.btnConnect2.setDisable(false);		
			}
		});
		 view.group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	           @Override
	           public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
	               // Has selection.
	               if (view.group.getSelectedToggle() != null) {
	                   RadioButton button = (RadioButton) view.group.getSelectedToggle();
	                   if(button.getText().equals("Deutsch")) {
	                	language = "D";
	               		view.btnC.setText("User erstellen");
	               		view.l.setText("Herzlich Willkommen im FHNW-Chat!");
	            		view.btnE.setText("App verlassen");
	            	    view.label24.setText("Sprache: ");
	            	    
	            	    view.lblUser2.setText("Benutzername:");
	            		view.lblPw2.setText("Passwort:");
	            		view.btnLogin.setText("Login");
	            		view.btnWeiter2.setText("Weiter");
	            		view.btnZurück.setText("Zurück");
	            		
	            		view.lblUser.setText("Benutzername:");
	            		view.lblPw.setText("Passwort:");
	            		view.btnCreate.setText("User erstellen");
	            		view.btnWeiter.setText("Weiter");		            	
	            		view.btnZurück2.setText("Zurück");
	            		
	            		view.logout.setText("Logout");
	            		view.btnCreateChat.setText("Chats aktualisieren");
	            		view.btnCC.setText("Neuen Chat erstellen");
	            		view.btnJoin.setText("Anmelden für neuen Chat");
	            		view.btnWeiter3.setText("Chat betreten");
	            		view.label29.setText("Wählen Sie einen der bestehen Chats aus und melden Sie sich dafür an (siehe unten): ");

	            		view.lblCC.setText("Name des Chats:");
	            		view.btnCCCreate.setText("Chat erstellen");
	            		view.btnBack.setText("Zurück zur Chat-Liste");
	            		
	            		view.label36.setText("Name des Chats: ");
	              		view.btnSend.setText("Senden");	
	                	   
	                   }
	                   if(button.getText().equals("Englisch")) {
		                	language = "E";
		               		view.btnC.setText("Create User");
		               		view.l.setText("Welcome to the FHNW-Chat!");
		            		view.btnE.setText("Exit the App");
		            	    view.label24.setText("Language: ");
		            	    
		            	    view.lblUser2.setText("Username:");
		            		view.lblPw2.setText("Password:");
		            		view.btnLogin.setText("Login");
		            		view.btnWeiter2.setText("Continue");
		            		view.btnZurück.setText("Go back");
		            		
		            		view.lblUser.setText("Username:");
		            		view.lblPw.setText("Password:");
		            		view.btnCreate.setText("Create user");
		            		view.btnWeiter.setText("Continue");		            	
		            		view.btnZurück2.setText("Go back");
		            		
		            		view.logout.setText("Logout");
		            		view.btnCreateChat.setText("Refresh Chats");
		            		view.btnCC.setText("Create new Chat");
		            		view.btnJoin.setText("Sign up for Chat");
		            		view.btnWeiter3.setText("Enter Chat");
		            		view.label29.setText("Select one of the existing Chats and sign up for the Chat (see below):");
		            		
		            		view.lblCC.setText("Name of the Chat:");
		            		view.btnCCCreate.setText("Create Chat");
		            		view.btnBack.setText("Back to Chat-list");
		            		
		            		view.label36.setText("Chat-name: ");
		              		view.btnSend.setText("Send");	

		                   }
	   
	               }
	               if (view.group.getSelectedToggle() == null) {
	                	language = "E";

	           }
	           }
	           });
		
		model.found.addListener((observable, oldValue, newValue) ->{
			if(newValue) {
				view.getStage().setScene(view.scene4);				
			}
		});
		
		view.btnC.setOnAction(e ->{
			view.getStage().setScene(view.scene2);
		});

		view.btnSend.setOnAction( event -> {
			if(!view.txtChatMessage.getText().isEmpty()) {
				model.sendMessage("SendMessage|"+model.token.getValue()+"|"+view.chatName.getText()+"|"+view.txtChatMessage.getText());
				view.txtChatMessage.setText("");
				}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning alert");
				 
		        alert.setContentText("Please enter values!");
		 
		        alert.showAndWait();
		       
			}
			
		});
		
		model.newestMessage.addListener( (o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) // Ignore empty messages
				view.txtChatArea.appendText(newValue + "\n");
		} );
		
		view.btnCreate.setOnAction( event -> {
			if(!view.txtUser.getText().isEmpty() && !view.txtPw.getText().isEmpty()) {
				model.sendMessage("CreateLogin|"+view.txtUser.getText()+"|"+view.txtPw.getText());
				}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning alert");
				 
		        alert.setContentText("Please enter values!");
		 
		        alert.showAndWait();
			}	
			
		});
		
		view.btnLogin.setOnAction( event -> {
			if(!view.txtUser2.getText().isEmpty() && !view.txtPw2.getText().isEmpty()) {
				model.sendMessage("Login|"+view.txtUser2.getText()+"|"+view.txtPw2.getText());

				}else {
				Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning alert");
					 
			        alert.setContentText("Please enter values!");
			 
			        alert.showAndWait();
			}	
		});
	
		model.found2.addListener( (o, oldValue, newValue) -> {
			if (newValue) { // Ignore empty messages
				view.btnWeiter.setDisable(false);
				view.labelOne.setText("");
			}
		} );
		model.found3.addListener( (o, oldValue, newValue) -> {
			if (newValue) { // Ignore empty messages
				view.btnWeiter2.setDisable(false);
				view.labelTwo.setText("");
			}
		} );
	
		model.found6.addListener( (o, oldValue, newValue) -> {
			if (newValue) { // Ignore empty messages
				view.labelOne.setText("Error! User already exists!");
			}
		} );
		model.found7.addListener( (o, oldValue, newValue) -> {
			if (newValue) { // Ignore empty messages
				view.labelTwo.setText("Error! Wrong username or password!");
			}
		} );
		model.found8.addListener( (o, oldValue, newValue) -> {
			if (newValue) { // Ignore empty messages
				view.labelThree.setText("Error! Chat already exists!");
			}
		} );
	
		view.btnL.setOnAction(e ->{
			view.getStage().setScene(view.scene5);
		});
		//2
		view.btnWeiter.setOnAction(e ->{
			view.getStage().setScene(view.scene5);

		});
		view.btnWeiter2.setOnAction(e ->{
			view.getStage().setScene(view.scene6);
			model.sendMessage("ListChatrooms|"+model.token.getValue());
		});
		
		model.getElements().addListener((ListChangeListener<String>) c -> {
			while (c.next()) {
				view.listView.scrollTo(c.getFrom());
			}
		});
		view.btnCreateChat.setOnAction(event -> {
			view.listView.getItems().clear();
			model.sendMessage("ListChatrooms|"+model.token.getValue());

			Iterator list = model.elements.iterator();
			while(list.hasNext()) {
				String listE = (String) list.next();
				if(!view.listView.getItems().contains(listE)) {
					view.listView.getItems().add(listE);
				} 
			}
			});
		
		view.btnCC.setOnAction(e ->{
			view.getStage().setScene(view.scene7);
		});
		
		view.btnCCCreate.setOnAction(e ->{
			if(!view.lblCCField.getText().isEmpty()) {
				model.sendMessage("CreateChatroom|"+model.token.getValue()+"|"+view.lblCCField.getText()+"|true");
				}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning alert");
				 
		        alert.setContentText("Please enter values!");
		 
		        alert.showAndWait();
			}	

		});
		view.btnBack.setOnAction(e ->{
			view.getStage().setScene(view.scene6);
			view.lblCCField.setText("");
			view.labelThree.setText("");
			model.found8.setValue(false);
			model.sendMessage("ListChatrooms|"+model.token.getValue());
		});
		
		view.btnJoin.setOnAction(e ->{
			value = view.listView.getSelectionModel().getSelectedItem();
			view.chatName.setText(value);
			if(value != null) {
				model.sendMessage("JoinChatroom|"+model.token.getValue()+"|"+value+"|"+view.txtUser2.getText());	 
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning alert");
				 
		        alert.setContentText("Please enter values!");
		 
		        alert.showAndWait();
			}			
		});
		
		model.found4.addListener( (o, oldValue, newValue) -> {
			if (newValue) { // Ignore empty messages
				view.btnWeiter3.setDisable(false);
			}
		} );
		
		view.btnWeiter3.setOnAction(e ->{
			view.getStage().setScene(view.scene3);
		});
		
		view.menu.exit.setOnAction(e ->{
			view.btnWeiter3.setDisable(true);
			model.found4.setValue(false);
			model.sendMessage("LeaveChatroom|"+model.token.getValue()+"|"+value+"|"+view.txtUser2.getText());
			view.getStage().setScene(view.scene6);
		});
		
		view.menu.logout.setOnAction(e ->{
			view.btnWeiter2.setDisable(true);
			view.btnWeiter3.setDisable(true);
			view.txtUser2.setText("");
			model.found2.setValue(false);
			model.found3.setValue(false);
			model.found4.setValue(false);
			model.found6.setValue(false);
			model.found7.setValue(false);
			model.found8.setValue(false);
			view.txtChatArea.clear();
			view.labelOne.setText("");
			view.labelTwo.setText("");
			view.labelThree.setText("");
			view.txtPw2.setText("");
			model.sendMessage("Logout");
			view.getStage().setScene(view.scene4);
		});
		
		view.logout.setOnAction(e ->{
			view.btnWeiter2.setDisable(true);
			view.btnWeiter3.setDisable(true);
			view.txtUser2.setText("");
			model.found2.setValue(false);
			model.found3.setValue(false);
			model.found4.setValue(false);
			model.found6.setValue(false);
			model.found7.setValue(false);
			model.found8.setValue(false);
			view.txtChatArea.clear();
			view.labelOne.setText("");
			view.labelTwo.setText("");
			view.labelThree.setText("");
			view.txtPw2.setText("");
			model.sendMessage("Logout");
			view.getStage().setScene(view.scene4);
		});
		
		view.btnZurück.setOnAction(e ->{
			model.found3.setValue(false);
			model.found6.setValue(false);
			model.found7.setValue(false);
			model.found8.setValue(false);
			view.labelOne.setText("");
			view.labelTwo.setText("");
			view.labelThree.setText("");
			view.getStage().setScene(view.scene4);
			view.txtUser2.setText("");
			view.txtPw2.setText("");
			view.btnWeiter2.setDisable(true);
		});
		
		view.btnZurück2.setOnAction(e ->{
			model.found2.setValue(false);
			model.found6.setValue(false);
			model.found7.setValue(false);
			model.found8.setValue(false);
			view.labelOne.setText("");
			view.labelTwo.setText("");
			view.labelThree.setText("");
			view.getStage().setScene(view.scene4);
			view.txtUser.setText("");
			view.txtPw.setText("");
			view.btnWeiter.setDisable(true);
		});
		
		view.btnE.setOnAction(e -> {
			model.disconnect();
			Platform.exit();
		});
		
		view.stage.setOnCloseRequest( event -> model.disconnect() );
		
	}
	
}