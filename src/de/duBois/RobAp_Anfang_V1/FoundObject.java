package de.duBois.RobAp_Anfang_V1;

//Diese Klasse steht für ein Objekt, dass die SmartKamera gefunden hat
//Es kann sowohl einen Spielstein als auch etwas anderes darstellen (type)

public class FoundObject {
	String type;
	double[] pixelKoord;
   
	
    FoundObject(String type, double[] pixelKoord, boolean farbe){
    	this.type=type;
    	this.pixelKoord=pixelKoord;
   
	}
	
	
	
}
