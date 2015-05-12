package de.duBois.RobAp_Anfang_V1;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Oberflaeche extends Frame
{
  TextField eingabe;
  Label     ausgabe;
  Label		hinweis; 
  Button    button0;
  Button    button1;
  Button    button2;

    public Oberflaeche( String fensterTitel )
  {
    super( fensterTitel );
    hinweis = new Label( "Text eingeben und mit Return abschliessen" );
    //eingabe = new TextField();
    ausgabe = new Label();
    button0 = new Button("Initialisierung");
    button1 = new Button("Spiel beginnen");
    button2 = new Button("Stein setzen");
    
    
    add( BorderLayout.SOUTH, hinweis );
    add( BorderLayout.NORTH, button0 );
    add( BorderLayout.EAST, button1 );
    add( BorderLayout.WEST, button2 );
    add( BorderLayout.CENTER,  ausgabe );
    
      
    this.addWindowListener(
      new WindowAdapter() {
        public void windowClosing( WindowEvent ev ) {
           RobAp.muehle0.beob0.cam0.camcom.close(); //Schlieﬂt alle Sockets, Stoppt den EmpfangsThread
        	dispose();
          System.exit( 0 ); } } );
  }

  void meineMethode()
  {
    ausgabe.setText( "Der eingelesene Text lautet: " + eingabe.getText() );
  }
}