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


public class CylinderII extends Cylinder{
	
	public CylinderII(){
		super();
		keys = fill();
	}

	public HashMap<Character, Character> fill() {
		HashMap<Character, Character> keys = new HashMap<Character, Character>();
		keys.put('A', 'Q');		keys.put('B', 'S');
		keys.put('C', 'U');		keys.put('D', 'G');
		keys.put('E', 'B');		keys.put('F', 'E');
		keys.put('G', 'C');		keys.put('H', 'L');
		keys.put('I', 'F');		keys.put('J', 'P');
		keys.put('K', 'Y');		keys.put('L', 'W');
		keys.put('M', 'K');		keys.put('N', 'N');
		keys.put('O', 'J');		keys.put('P', 'O');
		keys.put('Q', 'Z');		keys.put('R', 'V');
		keys.put('S', 'R');		keys.put('T', 'H');
		keys.put('U', 'A');		keys.put('V', 'X');
		keys.put('W', 'I');		keys.put('X', 'M');
		keys.put('Y', 'D');		keys.put('Z', 'T');
		return keys;
	}
}
