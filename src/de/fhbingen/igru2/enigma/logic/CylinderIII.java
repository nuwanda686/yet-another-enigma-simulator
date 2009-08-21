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

public class CylinderIII extends Cylinder{
		
	public CylinderIII(){
		super();
		keys = fill();
	}

	public HashMap<Character, Character> fill() {
		HashMap<Character, Character> keys = new HashMap<Character, Character>();
		keys.put('A', 'J');		keys.put('B', 'Q');
		keys.put('C', 'B');		keys.put('D', 'F');
		keys.put('E', 'L');		keys.put('F', 'O');
		keys.put('G', 'N');		keys.put('H', 'M');
		keys.put('I', 'G');		keys.put('J', 'T');
		keys.put('K', 'Z');		keys.put('L', 'A');
		keys.put('M', 'P');		keys.put('N', 'S');
		keys.put('O', 'Y');		keys.put('P', 'W');
		keys.put('Q', 'E');		keys.put('R', 'U');
		keys.put('S', 'H');		keys.put('T', 'C');
		keys.put('U', 'I');		keys.put('V', 'R');
		keys.put('W', 'V');		keys.put('X', 'D');
		keys.put('Y', 'K');		keys.put('Z', 'X');
		return keys;
	}
}
