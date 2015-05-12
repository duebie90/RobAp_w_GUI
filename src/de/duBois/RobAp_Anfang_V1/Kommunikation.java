package de.duBois.RobAp_Anfang_V1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;

public class Kommunikation extends Thread{

	static final int port1=44818; //tcp for implicit
	static final int buffer_size=1024;
	BufferedInputStream instream;
	//	 DataInputStream instream;
	 DataOutputStream outstream;
	 Socket server;
	 ServerSocket ssocket0; 
	 Kamera parent_kamera;
	 ReceiveThread receivethread;
	 
	Kommunikation(Kamera parent){
		this.parent_kamera=parent;
	}
	
	int createSocket(){
		
		try {
			init_socket();
			receivethread = new ReceiveThread(this, server);
			receivethread.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ssocket0.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ssocket0=null;
			server=null;
			return -1;
		}
		return 0;
	}
	
	
	int init_socket() throws IOException{

		ssocket0 = new ServerSocket(port1); 
		ssocket0.setSoTimeout(10000);
		server=ssocket0.accept();
	    //instream  = 	new DataInputStream(server.getInputStream());
		instream  = 	new BufferedInputStream(server.getInputStream());
		outstream =     new DataOutputStream(server.getOutputStream());
		if(server.isConnected() && instream!=null){	
		//	start();
		}
		return 0; 
	}
	
	
	int send(byte[] data){
		 try {
			outstream.write(data,0, data.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     try {
			outstream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	

	
	
	void async_receptor(byte[] data){ //
		RobAp.muehle0.beob0.cam0.received_data(data); //Daten an Top-Level-klasse weitergeben
	}
	
	
	
	
	boolean isConnected(){
		if (server==null)
				return false;
		else if (server.isConnected() && server.isClosed()==false )
			return true;
		else
			return false;
}
	
	
	@SuppressWarnings("deprecation")
	void close(){
		try {
			if(receivethread!=null)
				receivethread.stop(); //tötet den Thread
			receivethread=null;
			if(ssocket0!=null)
				ssocket0.close();
			if(server!=null)
				server.close();
			ssocket0=null;
			server=null;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
}
