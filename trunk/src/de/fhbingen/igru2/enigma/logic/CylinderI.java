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

public class CylinderI extends Cylinder {
	
	public CylinderI(){
		super();
		keys = fill();
	}

	public HashMap<Character, Character> fill() {
		HashMap<Character, Character> keys = new HashMap<Character, Character>();
		keys.put('A', 'T');		keys.put('B', 'L');
		keys.put('C', 'C');		keys.put('D', 'D');
		keys.put('E', 'O');		keys.put('F', 'Y');
		keys.put('G', 'H');		keys.put('H', 'M');
		keys.put('I', 'Q');		keys.put('J', 'P');
		keys.put('K', 'U');		keys.put('L', 'B');
		keys.put('M', 'S');		keys.put('N', 'I');
		keys.put('O', 'G');		keys.put('P', 'R');
		keys.put('Q', 'E');		keys.put('R', 'K');
		keys.put('S', 'W');		keys.put('T', 'N');
		keys.put('U', 'F');		keys.put('V', 'X');
		keys.put('W', 'J');		keys.put('X', 'V');
		keys.put('Y', 'A');		keys.put('Z', 'Z');
		return keys;
	}

}
