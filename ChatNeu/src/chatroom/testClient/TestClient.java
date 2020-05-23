package chatroom.testClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.Security;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.sun.net.ssl.internal.ssl.Provider;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is a really simple test client: It reads from the scanner, sends to the
 * server, and prints the server's response. This is all in plain-text - it is
 * up to the user to type in correctly formatted messages.
 */
public class TestClient extends Application{
	
	protected static CModel model;
	protected static CView view;
	protected static CController controller;
	
	public void start(Stage stage) {
		
		model = new CModel();
		view = new CView(model, stage);
		controller = new CController(model, view);
		view.start();		
	}
	public void stop() {
		if(view!=null) {
			view.stop();
		}
	}

	public static void main(String[] args) {
		launch(args);
	
	}
}
