package chatroom.testClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.Security;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.sun.net.ssl.internal.ssl.Provider;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CModel {
	
	protected String ipAddress = null;
	protected int port = 0;
	protected boolean secure = true;
	protected String YoN;
	protected static OutputStreamWriter socketOut;
	protected BooleanProperty found = new SimpleBooleanProperty(false);
	protected CView view;
	protected SimpleStringProperty newestMessage = new SimpleStringProperty();
	protected BooleanProperty found2 = new SimpleBooleanProperty(false);
	protected BooleanProperty found3 = new SimpleBooleanProperty(false);
	protected BooleanProperty found4 = new SimpleBooleanProperty(false);
	protected BooleanProperty found5 = new SimpleBooleanProperty(false);
	protected BooleanProperty found6 = new SimpleBooleanProperty(false);
	protected BooleanProperty found7 = new SimpleBooleanProperty(false);
	protected BooleanProperty found8 = new SimpleBooleanProperty(false);
	protected BooleanProperty found9 = new SimpleBooleanProperty(false);


	protected StringProperty token = new SimpleStringProperty("Hallo");
	protected final ObservableList<String> elements = FXCollections.observableArrayList();
	protected Socket socket;
	
	public void connect(String ip, int port, String YoN) {
		this.ipAddress = ip;
		this.port = port;
		this.YoN = YoN;
		
		boolean valid = false;
		while (!valid) {
			valid = validateIpAddress(ipAddress);
			if (valid) this.ipAddress = ip;
		}
		valid = false;
		while (!valid) {
			valid = validatePortNumber(Integer.toString(port));
			if (valid) this.port = port;
		}
		secure = YoN.equalsIgnoreCase("yes");
		socket = null;
		try {
			if (secure) {
				Security.addProvider(new Provider());
				System.setProperty("javax.net.ssl.trustStore", "truststore.ts");
				System.setProperty("javax.net.ssl.trustStorePassword", "trustme");
				SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
				socket = sslsocketfactory.createSocket(ipAddress, port);
				((SSLSocket) socket).startHandshake();
			} else {
				socket = new Socket(ipAddress, port);
			}
			System.out.println("Connected");
			this.found.setValue(true); 
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					socketOut = new OutputStreamWriter(socket.getOutputStream());
				// Create thread to read incoming messages
				Runnable r = new Runnable() {
					@Override
					public void run() {
						while (true) {
							String msg;
							try {
								msg = socketIn.readLine();
								System.out.println("Received: " + msg);					
								messageProcess(msg);
								messageShow(msg);
							} catch (IOException e) {
								break;
							}						
							if (msg == null) break; // In case the server closes the socket
						}
					}
				};
				Thread t = new Thread(r);
				t.start();
	
		}catch(Exception e) {
			this.found9.setValue(true);
		}
	}

	public void messageShow(String message) {
		this.newestMessage.set("");
		String [] s = message.split("\\|");
		if(s[0].equalsIgnoreCase("MessageText")) {
			String user = s[1];
			String msg = s[3];
			this.newestMessage.set(user+": "+msg);
			}
	}
	
	public void messageProcess(String msg) {
		String [] s = msg.split("\\|");
		if(s[1].equalsIgnoreCase("createlogin") && s[2].equalsIgnoreCase("true")) {
			this.found2.setValue(true);
			}
		if(s[1].equalsIgnoreCase("createlogin") && s[2].equalsIgnoreCase("false")) {
			this.found6.setValue(true);
			}
		if(s[1].equalsIgnoreCase("login") && s[2].equalsIgnoreCase("true")) {
			this.found3.setValue(true);
			String s2 = s[3];
			token.set(s2);
			}
		if(s[1].equalsIgnoreCase("login") && s[2].equalsIgnoreCase("false")) {
			this.found7.setValue(true);
			}
		if(s[1].equalsIgnoreCase("Listchatrooms") && s[2].equalsIgnoreCase("true")) {
			try {
			for(int i = 3; i< s.length; i++) {
				String s2 = s[i];
				addNewElement(s2);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			}
		if(s[1].equalsIgnoreCase("JoinChatroom") && s[2].equalsIgnoreCase("true")) {
			this.found4.setValue(true);
		}
		if(s[1].equalsIgnoreCase("SendMessage") && s[2].equalsIgnoreCase("true")) {
			this.found5.setValue(true);
		}
		if(s[1].equalsIgnoreCase("CreateChatroom") && s[2].equalsIgnoreCase("false")) {
			this.found8.setValue(true);
		}		
	}
	

	// getters and setters
	public ObservableList<String> getElements() {
		return elements;
	}
	public void addNewElement(String s) {
		elements.add(s);
	}
	public String getToken() {
		return token.get();
	}
	
	
	
	
	public void sendMessage(String message) {
		String line = message;
		try {
			socketOut.write(line + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sent: " + line);
}
	

protected static boolean validateIpAddress(String ipAddress) {
	boolean valid = false;
	String[] parts = ipAddress.split("\\.", -1);

	if (parts.length == 4) {
		valid = true;
		for (String part : parts) {
			try {
				int value = Integer.parseInt(part);
				if (value < 0 || value > 255) valid = false;
			} catch (NumberFormatException e) {
				// wasn't an integer
				valid = false;
			}
		}
	}
	if (!valid) {
		if (parts.length >= 2) {
			valid = true;
			for (String part : parts) {
				if (part.length() < 2) valid = false;
			}
		}
	}
	return valid;
}

protected static boolean validatePortNumber(String portText) {
	boolean formatOK = false;
	try {
		int portNumber = Integer.parseInt(portText);
		if (portNumber >= 1024 & portNumber <= 65535) {
			formatOK = true;
		} else {
			formatOK = false;
		}
	} catch (NumberFormatException e) {
		e.getClass().toString();
	}
	return formatOK;
}
	public String receiveMessage() {
	return newestMessage.get();
}
	public void disconnect() {
		if (socket != null)
			try {
				socket.close();
			} catch (IOException e) {
				// Uninteresting
			}
	}
	
}


