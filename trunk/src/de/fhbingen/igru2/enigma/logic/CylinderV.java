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

public class CylinderV extends Cylinder{

	public CylinderV(){
		super();
		keys = fill();
	}

	public HashMap<Character, Character> fill() {
		HashMap<Character, Character> keys = new HashMap<Character, Character>();
		keys.put('A', 'C');		keys.put('B', 'R');
		keys.put('C', 'A');		keys.put('D', 'U');
		keys.put('E', 'N');		keys.put('F', 'I');
		keys.put('G', 'Y');		keys.put('H', 'L');
		keys.put('I', 'V');		keys.put('J', 'F');
		keys.put('K', 'X');		keys.put('L', 'Z');
		keys.put('M', 'K');		keys.put('N', 'H');
		keys.put('O', 'B');		keys.put('P', 'J');
		keys.put('Q', 'G');		keys.put('R', 'Q');
		keys.put('S', 'W');		keys.put('T', 'M');
		keys.put('U', 'P');		keys.put('V', 'T');
		keys.put('W', 'E');		keys.put('X', 'D');
		keys.put('Y', 'O');		keys.put('Z', 'S');
		return keys;
	}
}