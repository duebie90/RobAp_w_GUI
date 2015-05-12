package de.duBois.RobAp_Anfang_V1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class action_listener implements ActionListener{

		String associated_name;
		Oberflaeche caller_oberflaeche;
			
		action_listener(String name, Oberflaeche oberflaeche){
				this.associated_name=name;
				this.caller_oberflaeche= oberflaeche;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				switch(associated_name) {
					case "bu0":
						RobAp.bu0_action();
						break;
					case "bu1":
						RobAp.bu1_action();
						break;
					case "bu2":
						RobAp.bu2_action();
						break;
					case "bu3":
				default:
				}
			}
	    }
