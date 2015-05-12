package de.duBois.RobAp_Anfang_V1;

import java.awt.Color;

public class Muehle {

	//Ki ki0; 
	Beobachter beob0;
	String zustand;  //"nix", "setzen", "ziehen", "springenP","springenR", "springen_beide", "siegR", "siegP", "fehler" 
	Spielstein[] SpielfeldInhalt;
	
	
	Muehle(){
		RobAp.meinFenster.ausgabe.setText("Mühle erzeugt");
		zustand="nix";
		beob0 = new Beobachter();
		SpielfeldInhalt = new Spielstein[18]; //es werden erstmal nur leere Objekte erzeugt, die Spielsteine selbst müssen später einzeln initialisiert werden
	}
	
	int start_game(){
		//initialisierung eines neuen spiels ggf abbruch eines alten
		RobAp.meinFenster.ausgabe.setText("Mühlespiel gestartet! \n Bitte ersten Stein setzen und eingabe drücken.");
		zustand="nix";
		return 0;
	}
	
	
	String step() //bei einem Aufruf von play() wird immer EIN Spielzug des Roboters ausgeführt
	{
		//Änderungen seit dem letzten Aufruf ERKENNEN und VERARBEITEN (Beobachter verwenden)
		//	beob0.SucheSteine(); //-->liefert Spielstein Vektor
			//Vergleichen mit Spielfeldinhalt
		
		//Zustand & Spielbrett aktualisieren
		//KI fragen, welches der nächste Spielzug sein soll
		//AUSFÜHREN des Spielzugs (Motorik)  
		//Zustand & Spielbrett aktualisieren
		RobAp.meinFenster.ausgabe.setText("Roboter beginnt Sie zu vernichten: bitte warten...");
		RobAp.meinFenster.button1.setBackground(Color.red);
		RobAp.wait_ms(2000); //zwei sekunden warten
		RobAp.meinFenster.button1.setBackground(Color.green);
		RobAp.meinFenster.ausgabe.setText("Bitte nächsten Stein setzen/ ziehen");
		
		
		return zustand;
	}
	
	String getState(){
		return zustand;
	}
	
	
	
}
