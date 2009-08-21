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


public class Reflektor {
	private final HashMap<Character, Character> keys;
	
	public Reflektor() {
		super();
		keys = new HashMap<Character, Character>();
		keys.put('A', 'H');		keys.put('H', 'A');
		keys.put('B', 'S');		keys.put('S', 'B');
		keys.put('C', 'O');		keys.put('O', 'C');		
		keys.put('D', 'J');		keys.put('J', 'D');
		keys.put('E', 'V');		keys.put('V', 'E');
		keys.put('F', 'Y');		keys.put('Y', 'F');
		keys.put('G', 'Q');		keys.put('Q', 'G');
		keys.put('I', 'U');		keys.put('U', 'I');
		keys.put('K', 'L');		keys.put('L', 'K');
		keys.put('M', 'Z');		keys.put('Z', 'M');
		keys.put('N', 'W');		keys.put('W', 'N');
		keys.put('P', 'X');		keys.put('X', 'P');
		keys.put('R', 'T');		keys.put('T', 'R');		
	}
	
	public char crypt( char c ){
		assert c>='A'&&c<='Z': "char Fehler(Reflektor crypt) "+c;
		return keys.get( c );
	}

	public char decrypt( char c ){
		return keys.get( c );
	}
}
