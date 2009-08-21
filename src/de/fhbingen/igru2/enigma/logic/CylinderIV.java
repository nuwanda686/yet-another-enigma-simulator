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


public class CylinderIV extends Cylinder{

	public CylinderIV(){
		super();
		keys = fill();
	}
	
	public HashMap<Character, Character> fill() {
		HashMap<Character, Character> keys = new HashMap<Character, Character>();
		keys.put('A', 'J');		keys.put('B', 'E');
		keys.put('C', 'S');		keys.put('D', 'L');
		keys.put('E', 'N');		keys.put('F', 'M');
		keys.put('G', 'Y');		keys.put('H', 'T');
		keys.put('I', 'Q');		keys.put('J', 'Z');
		keys.put('K', 'G');		keys.put('L', 'V');
		keys.put('M', 'O');		keys.put('N', 'C');
		keys.put('O', 'D');		keys.put('P', 'A');
		keys.put('Q', 'R');		keys.put('R', 'P');
		keys.put('S', 'H');		keys.put('T', 'U');
		keys.put('U', 'B');		keys.put('V', 'I');
		keys.put('W', 'K');		keys.put('X', 'W');
		keys.put('Y', 'F');		keys.put('Z', 'X');
		return keys;		
	}	
}


