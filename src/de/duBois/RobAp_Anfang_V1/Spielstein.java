package de.duBois.RobAp_Anfang_V1;

public class Spielstein {
  private int [] coords;
  private boolean color;
  
  Spielstein(boolean color, int coord0, int coord1){ //Dem Konstruktor werden direkt Koordinaten und Farbe mitgegeben
	  coords[0]=coord0; coords[1]=coord1;
	  this.color=color;  //1=black 0=white
  }

  boolean getColor(){
	  return color;
  }
  int[] getCoords(){
	  return coords;
  }
  
  void setCoords(int[] new_coords){  //notwendig wenn Spielstein verschoben wird
	  this.coords=new_coords;
  }
	
	
}
