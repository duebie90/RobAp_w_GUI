package de.duBois.RobAp_Anfang_V1;


import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.Math;





//Eine Instanz dieser Klasse könnte zukünftig von der RobotApplication erzeugt werden
//Dabei müsste dann nur eine Referenz des dort erzeugten "Robot-Objectes" und des Gripper Objectes übergeben werden 
public class RobAp {

	static Muehle muehle0;
	static RobAp robap1;
	public static Oberflaeche meinFenster;
	 
	
	public static void main(String[] args) {
		
		meinFenster = new Oberflaeche( "Roboter Mühle-Versuch 1" );
	    meinFenster.setSize( 800, 200 );
	
	    meinFenster.setVisible( true );
	    meinFenster.ausgabe.setAlignment(Label.CENTER);
	    meinFenster.ausgabe.setText("Bereit");
	    meinFenster.button0.setLabel("Mühlespiel starten");
	    meinFenster.button1.setLabel("Spielzug beendet");
	    meinFenster.button2.setLabel("Kommunikation initialisieren");
	    		
		  
	    
	    meinFenster.button0.addActionListener(new action_listener("bu0", meinFenster) );
	    meinFenster.button1.addActionListener(new action_listener("bu1", meinFenster) );
	    meinFenster.button2.addActionListener(new action_listener("bu2", meinFenster) );
	        
	    muehle0= new Muehle();
	    
	    
	    
	    
	    //Nutzer macht ersten Spielzug
	    //auf Nutzereingabe warten, dann:
	    
	    	
	    	    
	  while(true){
		  ; //auf besseres Wetter warten...
	  }
}
	static void bu0_action(){
		muehle0.start_game();
	}
	
	static void bu1_action(){
		switch(muehle0.getState()){
			case "siegP":
					break;
			case "siegR":
					break;
			case "fehler":
				break;
			default:
				muehle0.step();
		}
	}
	
	static void bu2_action() {
		meinFenster.ausgabe.setText("Warten auf Verbindung durch Client...(10 Sekunden)");
		if(RobAp.muehle0.beob0.cam0.camcom.isConnected()){
			RobAp.muehle0.beob0.cam0.camcom.close();
			meinFenster.button2.setBackground(Color.GRAY);
			meinFenster.ausgabe.setText("getrennt!");
			wait_ms(1000);
			meinFenster.ausgabe.setText("...");
			return;
		}
		if(RobAp.muehle0.beob0.cam0.camcom.createSocket()==0){
			meinFenster.button2.setLabel("Trennen");
			meinFenster.button2.setBackground(Color.GREEN);
			meinFenster.ausgabe.setText("Verbunden");
			//muehle0.beob0.cam0.camcom.run(); //starte asynchronen Nachrichtenempfang
			wait_ms(1000);
			meinFenster.ausgabe.setText("...");
			}
		else{
			meinFenster.button2.setBackground(Color.RED);
			meinFenster.ausgabe.setText("gescheitert!");
			wait_ms(1000);
			meinFenster.ausgabe.setText("...");
			}
	

	}
	
	
	static void async_receptor(byte[] data){ //ToDo: verschieben in Kommunikation
		String received= new String(data);
		meinFenster.hinweis.setText("Asynchron empf: "+ received  );
		
	}

	
	
	
	
	
	static void wait_ms(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	
	
	

}


