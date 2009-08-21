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

public class Plugboard {
    private HashMap<Character, Character> connections;

    public Plugboard() {
        connections = new HashMap<Character, Character>();
    }

    public Plugboard(HashMap<Character, Character> connections) {
        this.connections = connections;
    }

    public HashMap<Character, Character> getConnections() {
        return connections;
    }

    public boolean addConnection(Character from, Character dest) {
        if (connections.containsKey(from) || connections.containsValue(from) ||
            connections.containsKey(dest) || connections.containsValue(dest) ) {
            return false;
        }
        connections.put(from, dest);
        connections.put(dest, from);
        return true;
    }

    public boolean removeConnection(Character from) {
        if (connections.containsKey(from)) {
            connections.remove(from);
            connections.remove(connections.get(from));
            return true;
        } else {
            return false;
        }
    }

    // Funktion zum codieren/dekodieren auf dem Hinweg zum Reflektor
	public char cryptI(char c ){
        if (connections.containsKey(c)) {
            return connections.get(c);
        } else {
            return c;
        }
	}

	// Funktion zum codieren/dekodieren auf dem Rückweg vom Reflektor
	public char cryptII( char c ) {
        if (connections.containsValue(c)) {
            for( char a = 'A'; a <= 'Z'; a++ ) {
            	if( connections.containsKey(a) && connections.get(a)==c ){
            		return a;
            	}
            }
            return c;
        } else {
            return c;
        }
	}
}