package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InnerDoorServer {
	public static void main(String[] args) {
		new InnerDoorServer().createServer(1127,"MFiq09@mo0SA9#0F+20&Q");
	}
	public void createServer(int port,String password) {
		try (ServerSocket server = new ServerSocket(port)) {
			if (!server.isBound()) return;
			while(true)runServerService(server.accept());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void runServerService(Socket connection) {
		runServerInputListener(connection);
		runServerOutputProducer(connection);
	}
	public void runServerInputListener(Socket connection) {
		new Thread(new Runnable (){
			String line = null;
			public void run(){
				try{
					final BufferedReader serverInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					boolean read=false;
					while ((line = serverInput.readLine()) != null) {
						if(read)System.out.println(line);
						if(line.equals(""))read=true;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
	public void runServerOutputProducer(Socket connection) {
		new Thread(new Runnable (){
			public void run(){
				try{ 
					final OutputStream serverOutput = connection.getOutputStream();
				//BODY
//						final String body="<html><body>OK</body></html>";
					final String body="{\"resp\": \"YO!!!\"}";
				//HEAD
					final String status="HTTP/1.0 200 OK";
					final String server="Server: HTTP server/0.1";
					final String contentType="Content-type: text/plain; charset=UTF-8";
					final String contentLength="Content-Length: "+body.length();
					final String HEAD=status+"\n"+server+"\n"+contentType+"\n"+contentLength;
				//RESPONSE
					final String response=HEAD+"\n\n"+body;
					serverOutput.write(response.getBytes());
					serverOutput.flush();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
}