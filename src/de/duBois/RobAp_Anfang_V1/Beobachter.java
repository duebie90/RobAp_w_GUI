package de.duBois.RobAp_Anfang_V1;


import java.lang.Math.*;
import java.lang.Math;
public class Beobachter {
	Kamera cam0;
	//FoundObject[] SuchergebnisSteine;
	Spielstein[] Spielfeld;
	
	double[][][] spielfeld_positionen;
	double[] laengen; //Abstände vom Ursprung in mm Spielfeldkoordinaten
	
	double[] spielfeld_translation; 	//Translation für Transformation von Kamerakoordinaten auf SpielfeldKoord.
	double spielfeld_drehwinkel;				//Rotation für Transformation von Kamerakoordinaten auf SpielfeldKoord.
	double[] kamera_translation; 	//Translation für Transformation von Kamerakoordinaten auf SpielfeldKoord.
	double kamera_drehwinkel;				//Rotation für Transformation von Kamerakoordinaten auf SpielfeldKoord.
	Integer[][] stones_kk;
	Integer[][] marker_kk;
	double mm_pixel=0;
	int[] Lxy;//Spielfeld constraints enthalten x bzw y Werte für horizontale bzw. vertikale Linien in Spielfeldkoordinaten (original-skaliert) 
	
	
	
	Beobachter(){
			cam0 = new Kamera(this);
			laengen=new double[7];
			laengen[0]=1;
			laengen[1]=2;
			laengen[2]=3;
			laengen[3]=4;
			laengen[4]=5;
			laengen[5]=6;
			laengen[6]=7;
		
			//SuchergebnisSteine = new FoundObject[2];
	}
	
	void receive_information(int[][]stones, int[][] marker){
		//Informationen über Steine und Marker sind eingetroffen
		//Sie sind Pixeln/ KameraKoordinaten
		stones_kk=new Integer[40][2];
		marker_kk=new Integer[4][2];
		//stones_kk=new Integer(stones);
		spielfeld_transf_vorbereiten(marker);
		spielfeld_constraints_erzeugen(marker);
		steine_einordnen(stones);
		}
	
	int kamera_position_init(){
		//Frame mark0_rr= new Frame(getApplicationData().getFrame("/camera_mark0"));
		//Frame mark1_rr= new Frame(getApplicationData().getFrame("/camera_mark1"));
		double diff_mm=0;
		double diff_pixel=0;
		int i=0;
		for(i=0;i<=2;i++){
			if(marker_kk[i][0]==0 || marker_kk[i][1]==0){ //testen ob die ersten 3 Marker werte verschieden von Null aufweisen
				return -1;							//wenn nicht, abbruch
			}			
		}
		double marker_0_x, marker_1_x, marker_0_y, marker_1_y;
		marker_0_x=marker_kk[0][0];
		marker_1_x=marker_kk[1][0];
		marker_0_y=marker_kk[0][1];
		marker_1_y=marker_kk[1][1];
		
		
		double marker_0_abs=java.lang.Math.sqrt(java.lang.Math.pow(marker_0_x,2) + java.lang.Math.pow(marker_0_y,2));
		double marker_1_abs=java.lang.Math.sqrt(java.lang.Math.pow(marker_1_x,2) + java.lang.Math.pow(marker_1_y,2));
		
		if(marker_0_abs>marker_1_abs){
			double[] temp = {0,0};
			temp[0]=marker_0_x;
			temp[1]=marker_0_y;
			marker_0_x=marker_1_x;
			marker_0_y=marker_1_y;
			marker_1_x=temp[0];
			marker_1_y=temp[1];
			marker_kk[0][0]=(int) java.lang.Math.round(marker_0_x);
			marker_kk[0][1]=(int) java.lang.Math.round(marker_0_y);
			marker_kk[1][0]=(int) java.lang.Math.round(marker_1_x);
			marker_kk[1][1]=(int) java.lang.Math.round(marker_1_y);
		}
		
		//Marker Matrix sortieren
		//Marker 0 sollte der am nächsten am Ursprung gelegene sein
		//Differenz zwischen getechten Punkten in mmm
		//diff_mm=abs(mark0_rr.distanceTo(mark1.rr));
		

		
		double temp_sqrt=java.lang.Math.pow(marker_1_x-marker_0_x,2) + java.lang.Math.pow(marker_1_y-marker_0_y,2);
		diff_pixel=java.lang.Math.sqrt(temp_sqrt);
			
		mm_pixel=diff_mm/diff_pixel;
	    
		//kamera_translation[0]=mark0_rr.getX()+marker_0_x*mm_pixel;
		//kamera_translation[1]=mark0_rr.getY()+marker_0_y*mm_pixel;
		
		double phi_kk=	java.lang.Math.atan((marker_1_y-marker_0_y)/(marker_1_x-marker_0_x));
		//double phi_rr=	java.lang.Math.atan((mark1.getY()-mark0.getY())/(mark1.getX()-mark0.getX()));
		//double phi_kr=phi_rr-phi_kk;
			return 0;
	}
	
	
	
    
    
	void spielfeld_transf_vorbereiten(int[][]marker){
		double marker_0_x, marker_1_x, marker_0_y, marker_1_y;
		marker_0_x=marker_kk[0][0];
		marker_1_x=marker_kk[1][0];
		marker_0_y=marker_kk[0][1];
		marker_1_y=marker_kk[1][1];
	
		spielfeld_drehwinkel=	java.lang.Math.atan((marker_1_y-marker_0_y)/(marker_1_x-marker_0_x));
		spielfeld_translation[0]=marker_0_x;
		spielfeld_translation[1]=marker_0_y;

	}
	
	void spielfeld_constraints_erzeugen(int[][]marker ){
		//bestimme Kantenlänge: jeweils Differenz bestimmen; Mittelwert bilden
		//aus Kantenlänge die X bzw. Y Koordinaten der Linien bestimmen (Spielfeldkoordinaten)
		//Lxy1, Lxy2,...Lxy7 vertikale bzw horizontale Linien
	int i=0;
	int j=0;
	for(j=0;j<7;j++){
		for(i=0;i<7;i++){
			spielfeld_positionen[i][j][0]=laengen[i];  //[x][y][x/y]
			spielfeld_positionen[i][j][1]=laengen[j];
					}
	}
	}

	void steine_einordnen(int[][]stones){
		//für alle Koordinaten in int[][] stones_int
		//jeweils transformieren auf Spielfeldkoordinaten
		//und x-wert mit LNx   bzw. y-Wert mit Lny vergleichen
		//im Raster entsprechend im Spielfeld -->Spielstein-Objekt.coords speichern
			
	}
	
	
//	Spielstein[] SucheSteine(){
//		//Aufruf der Kamera zur Suche nach definierten Objekten
//		//lifert Pixelwerte in Form des FoundObject Vektors
//	FoundObject[] SuchergebnisSteine = null;
//		//daraus müssen nun die tatsächlichen Koordinaten/ Spielfeldkoordinaten berechnet werden
//	ErzeugeSpielsteine(SuchergebnisSteine);
//		//return ErzeugeSpielsteine(SuchergebnisSteine);   
//		return null;
//	}
	

	
	
	
Spielstein[] ErzeugeSpielsteine(FoundObject[] Suchergebnis ){
	//Berechnung der tatsächlichen Spielsteine
	//Erzeugung der Objekte um diese zu speichern
	Spielstein[] tempSteine = new Spielstein[18];
	//Beispiel
	//Umrechnung Pixel-->SpielfeldKoordinaten
	tempSteine[0]=new Spielstein(true, 2, 5); //Farbe: Schwarz Position: 2/5	
	//return tempSteine;
	return null;


}
	
	
	
	
}
