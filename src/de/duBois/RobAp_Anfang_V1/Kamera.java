package de.duBois.RobAp_Anfang_V1;

import java.util.logging.Handler;

public class Kamera {
	
	Beobachter parent;
	Kommunikation camcom;
	int[][] 	temp_stones_int; //Suchergebnis wird hier drinn zwischengespeichert Spalten sind x/y
	int[][] 	temp_marks_int; //Suchergebnis wird hier drinn zwischengespeichert Spalten sind x/y
	
	
	Kamera(Beobachter parent){
		camcom = new Kommunikation(this);
		this.parent=parent;	//merken wer das Elternobjekt ist, damit Methoden daraus aufgerufen werden können
	}
	
	
	
	void received_data(byte[] data){ //ToDo: verschieben in Kommunikation
		String received_String= new String(data);
		RobAp.meinFenster.hinweis.setText("Asynchron empf: "+ received_String  );
	//daten in zerlegen N
		NachrichtZerlegen(received_String);
		parent.receive_information(temp_stones_int, temp_marks_int);
	}

//	FoundObject[] AbfrageSteine(){
//		
//		//Suche nach definierten Objekten in Auftrag geben
//		//Auf Reaktion warten (in KommunikationsRoutine)
//		return NachrichtFilternSteine(); //Gefundene Objekte als Vektor aus FoundObject zurückgeben
//	}
	
	void NachrichtZerlegen(String data){
		//Aus der Kommunikation die Pixelwerte der gefundenen Steine extrahieren
		//und als Matrix zurückgeben
		temp_stones_int=getStones(data); //liefert die Koordinaten der Steine: 		integer Matrix 		
		temp_marks_int=getMarks(data); //liefert die Koordinaten der Marker: 		integer Matrix
		//int a=data.length;
		//FoundObject[] ergebnis=new FoundObject[data.length/2];
	}
	
	int[][] getStones(String data){ //Beginnt am Anfang und geht bis zu ersten ";"
		int[][] res = new int[40][2]; //Speichert das Ergenis der Zerlegung  (Es gibt maximal 40 erkannte Steine (mit Dopplungen) ) 
		int res_counter=0; 	//legt fest an welcher stelle das Ergebnis gespeichert werden soll
		int x_y=0;			//legt fest ob es sich um x- oder y Koordinate handelt (alternierend)
		int i=0; int j=0; 
		String 	temp_teil_string=new String("");
		int 	temp_int=0;
		
		while(i<data.length()-1 && data.charAt(i)!=';'){
			if (j<data.length()-1) j++; 
			while(j<data.length()-1 && data.charAt(j)!=',' && data.charAt(j)!=';' ){		j++;		}
			temp_teil_string=data.substring(i,j);		//Teilstring extrahieren
			try {
			temp_int=Integer.parseInt(temp_teil_string);//In Integer parsen
			}
			catch(NumberFormatException e){temp_int=0;}
			res[res_counter][x_y]=temp_int;				//dem Integer-Array hinzufügen
			
			if (x_y==1){ //abwechslen x und y Koordinaten abscheichern
				x_y=0; res_counter++;}
			else
				x_y=1;
			i=j;
			if(i<data.length() && data.charAt(i)==',' ) i++;
		}
		return res;
	}
	
	int[][] getMarks(String data){//Beginnt am ersten ";" und geht bis zum zweiten ";"
		int[][] res = new int[4][2]; //Es gibt maximal 4 Marker 
		int res_counter=0; 	//legt fest an welcher stelle das Ergebnis gespeichert werden soll
		int x_y=0;			//legt fest ob es sich um x- oder y Koordinate handelt (alternierend)
		int i=0; int j=0; 
		String 	temp_teil_string=new String("");
		int 	temp_int=0;
		
		do{i++;}while(i<data.length() && data.charAt(i)!=';'); //gehe zur Position des ersten Semikolons
		i++; //und noch einen weiter
		j=i;
		while(i<data.length()-1 && data.charAt(i)!=';'){
			if (j<data.length()-1) j++; 
			while(j<data.length()-1 && data.charAt(j)!=',' && data.charAt(j)!=';' ){		j++;		}
			temp_teil_string=data.substring(i,j);		//Teilstring extrahieren
			try {
				temp_int=Integer.parseInt(temp_teil_string);//In Integer parsen
				}
				catch(NumberFormatException e){temp_int=0;}
			res[res_counter][x_y]=temp_int;				//dem Integer-Array hinzufügen
			
			if (x_y==1){ //abwechslen x und y Koordinaten abscheichern
				x_y=0; res_counter++;}
			else
				x_y=1;
			i=j;
			if(i<data.length() && data.charAt(i)==',' ) i++;
		}
		return res;
	}
	

}
