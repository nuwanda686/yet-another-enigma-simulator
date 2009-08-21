/*
 * ENIGMA-Simulator - Grundlagen der Informatik 2 - FH Bingen
 *
 * Eine Java-Simulation der Verschlüsselungsmaschine Enigma
 *
 * Copyright (C) 2009, Oliver Martin, Jonas Kleemann und Andreas Trepczik
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Oliver Martin, Jonas Kleemann und Andreas Trepczik wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy us a beer in return.
 */

package de.fhbingen.igru2.enigma.logic;

import java.util.HashMap;

public class Hardware {
    private Plugboard plugboard;
	private Cylinder rotorI;
	private Cylinder rotorII;
	private Cylinder rotorIII;
	private Reflektor reflektor;
	private HashMap<String, Cylinder> rotorChoice;
	
	public Hardware() {
        this.plugboard = new Plugboard();
		this.reflektor = new Reflektor();
		this.rotorChoice = new HashMap<String, Cylinder>();
		setRotorChoiceMap();
	}
	
	// Kodieren/dekodieren eines Buchstaben
	public char crypt( char c ) {
		c = plugboard.cryptII( rotorI.cryptII( rotorII.cryptII( rotorIII.cryptII( reflektor.crypt( rotorIII.cryptI( rotorII.cryptI( rotorI.cryptI( plugboard.cryptI( c ) ) ) ) ) ) ) ) );
		setRotorenPos();
		return c;
	}
	
	// Kodieren/dekodieren eines Strings
	public String crypt( String s ) {
		String w = new String();
		for( int i = 0; i<s.length(); i++)
			w += crypt( s.charAt( i ) );
		return w;
	}
	
	public void resetRotoren() {
		rotorI.setRotorPos( 0 );
		rotorII.setRotorPos( 0 );
		rotorIII.setRotorPos( 0 );
	}

    public Plugboard getPlugboard() {
        return plugboard;
    }

    public void setPlugboard(Plugboard plugboard) {
        this.plugboard = plugboard;
    }

	public void setRotorChoiceMap() {
		rotorChoice.put( "CylinderI", new CylinderI() );
		rotorChoice.put( "CylinderII", new CylinderII() );
		rotorChoice.put( "CylinderIII", new CylinderIII() );
		rotorChoice.put( "CylinderIV", new CylinderIV() );
		rotorChoice.put( "CylinderV", new CylinderV() );
	}
	
	// Der erste Rotor mit um 1 hochgedreht, bei überlauf wird der nächste Rotor auch um 1 erhöht...
	public void setRotorenPos(){
		rotorIII.incRotorPos( rotorII.incRotorPos( rotorI.incRotorPos( 1 ) ) );
	}

	public void setRotorI( Cylinder rotor){
		this.rotorI = rotor;		
	}
	
	public void setRotorII( Cylinder rotor){
		this.rotorII = rotor;		
	}

	public void setRotorIII( Cylinder rotor){
		this.rotorIII = rotor;		
	}

	public Cylinder getRotorI() {
		return rotorI;
	}

	public Cylinder getRotorII() {
		return rotorII;
	}

	public Cylinder getRotorIII() {
		return rotorIII;
	}

	public Reflektor getReflektor() {
		return reflektor;
	}

	public HashMap<String, Cylinder> getRotorChoice() {
		return rotorChoice;
	}
}
