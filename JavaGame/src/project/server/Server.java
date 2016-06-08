package project.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public ServerSocket server;
	public Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	
	public void startRunning(){
		
		try {
			server = new ServerSocket(6789, 100);
			
			while(true){
				try{
					
				waitConnect();
				setUpStreams();
				whilecCommunicating();
				
				}catch(EOFException e){
					e.printStackTrace();
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void whilecCommunicating() throws IOException {
		
		int i = 0 ;
		do{
			
			System.out.println("yay");
			
			
		}while(i == 0);
	}
	
	private void close(){
		
		try{
			
			out.close();
			in.close();
			connection.close();
		}catch(IOException e){
			e.printStackTrace();
			
			
		}
		
	}
	
	
	private void setUpStreams() throws IOException{
		
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		
		in = new ObjectInputStream(connection.getInputStream());

	}

	private void waitConnect() throws IOException{
		
		connection = server.accept();
		
	}
	
	
}
