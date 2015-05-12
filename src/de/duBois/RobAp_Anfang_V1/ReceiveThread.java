package de.duBois.RobAp_Anfang_V1;

import java.io.IOException;
import java.net.Socket;

public class ReceiveThread extends Thread {
	Kommunikation parent;
	final int buffer_size=1024;
	Socket server;
	ReceiveThread(Kommunikation parent, Socket server){
		this.parent=parent;
		this.server=server;
	}
	
	
	public void run(){   //run methode wird ausgeführt, wenn der Thread gestartet wird (Thread.start() )
		while(true){
			byte[] temp_receive= new byte[buffer_size];
			try {
				if( server!=null && parent.instream!=null)
					if(server.isConnected() && server.isClosed()==false){	
					//instream.read(temp_receive,0,temp_receive.length);
					parent.instream.read(temp_receive,0,buffer_size);
					parent.async_receptor(temp_receive);}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	
	
}
