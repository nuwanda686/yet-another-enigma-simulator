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

package de.fhbingen.igru2.enigma;

import de.fhbingen.igru2.enigma.logic.Hardware;
import de.fhbingen.igru2.enigma.ui.MainFrame;

public class EnigmaSim {
	public static void main(String[] args) {
		Hardware hardware = new Hardware();
		//new Machine( hardware );
        MainFrame ui = new MainFrame();
        ui.setHardware(hardware);
        ui.setVisible(true);
	}
}
