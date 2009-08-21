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

public abstract class Cylinder {
	private int rotorPos = 1;
	protected HashMap<Character, Character> keys;
	
	public Cylinder(){
		super();
	}
	
	public Cylinder(HashMap<Character, Character> keys) {
		super();
		this.keys = keys;	
	}

	public abstract HashMap<Character, Character> fill();

	// Funktion zum codieren/dekodieren auf dem Hinweg zum Reflektor
	public char cryptI(char c ){
		assert c>='A'&&c<='Z': "char Fehler, "+c+" "+rotorPos; 
		char key = ( charTest( (char)(rotorPos + c) ) );
		assert key>='A'&&key<='Z': "key Fehler, "+key+" "+rotorPos;
		c = keys.get(key);
		return c;
	}
	
	// Funktion zum codieren/dekodieren auf dem Rückweg zum Reflektor
	public char cryptII( char c ){
		for( char a = 'A'; a <= 'Z'; a++ ){
			assert c>='A'&&c<='Z': "char Fehler, "+c+" "+rotorPos;
			if( keys.get(a)==c ){
				return charTest((char)( a - rotorPos ));
			}
		}
		assert false: "cryptII char Fehler "+c;
		return c;
	}
	
	public int incRotorPos( int i){
		if( i==0 )
			return 0;
		if( rotorPos==26 ){
			rotorPos = 1;	
			return 1;
		}
		rotorPos++;
		return 0;
	}
	
	public void setRotorPos( int count){
		assert count>=0&&count<=24: "RotorPos falsch!! "+count;
		rotorPos = count;
	}
	
	public int getRotorPos(){
		return rotorPos;
	}
	
	// Test, ob der Buchstabe zulässig ist
	public char charTest( char c ){
		return underflowTest( overflowTest( c ) );
	}
	
	// Hilfsfunktion für charTest
	public char underflowTest( char c ){
		return (char)( (c<65) ? (91-(65-c)) : c);
	}

	// Hilfsfunktion für charTest
	public char overflowTest( char c ){
		return (char)( (c>90) ? (c-26) : c);
	}
}
