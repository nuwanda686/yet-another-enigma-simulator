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

package de.fhbingen.igru2.enigma.ui;

import de.fhbingen.igru2.enigma.logic.Hardware;
import de.fhbingen.igru2.enigma.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@Deprecated
@SuppressWarnings("serial")
public class Machine extends JFrame implements MouseWheelListener {
	
	protected JFrame enigmaFrame = new JFrame("Java Enigma-Simulator");
	
	// Hardware
	protected Hardware hardware;
	
	// Rotoren
	protected JPanel rotorPanel = new JPanel( new GridLayout(2, 3) );
	
	protected JSpinner rotorOne = new JSpinner();
	protected JSpinner rotorTwo = new JSpinner();
	protected JSpinner rotorThree = new JSpinner();
	
	private String[] rotors = { "CylinderI", "CylinderII", "CylinderIII", "CylinderIV", "CylinderV" };
	protected JComboBox rotorOneBox = new JComboBox( rotors );
	protected JComboBox rotorTwoBox = new JComboBox( rotors );
	protected JComboBox rotorThreeBox = new JComboBox( rotors );	
	
	// Brett (Lichter + Knöpfe)
	protected JPanel boardPanel = new JPanel();
	
	// Lichter
	protected JPanel lightsPanel = new JPanel();

	protected JPanel[] lightList = new JPanel[26];
	
	protected JPanel lightQ = new JPanel();	protected JPanel lightW = new JPanel();
	protected JPanel lightE = new JPanel();	protected JPanel lightR = new JPanel();
	protected JPanel lightT = new JPanel();	protected JPanel lightZ = new JPanel();
	protected JPanel lightU = new JPanel();	protected JPanel lightI = new JPanel();
	protected JPanel lightO = new JPanel();
	
	protected JPanel lightA = new JPanel();	protected JPanel lightS = new JPanel();
	protected JPanel lightD = new JPanel();	protected JPanel lightF = new JPanel();
	protected JPanel lightG = new JPanel();	protected JPanel lightH = new JPanel();
	protected JPanel lightJ = new JPanel();	protected JPanel lightK = new JPanel();
	protected JPanel lightL = new JPanel();
	
	protected JPanel lightP = new JPanel();	protected JPanel lightY = new JPanel();
	protected JPanel lightX = new JPanel();	protected JPanel lightC = new JPanel();
	protected JPanel lightV = new JPanel();	protected JPanel lightB = new JPanel();
	protected JPanel lightN = new JPanel();	protected JPanel lightM = new JPanel();
	
	// Knöpfe
	protected JPanel buttonPanel = new JPanel();
	
	protected JButton[] buttonList = new JButton[26];
	
	protected JButton buttonQ = new JButton("Q");	protected JButton buttonW = new JButton("W");
	protected JButton buttonE = new JButton("E");	protected JButton buttonR = new JButton("R");
	protected JButton buttonT = new JButton("T");	protected JButton buttonZ = new JButton("Z");
	protected JButton buttonU = new JButton("U");	protected JButton buttonI = new JButton("I");
	protected JButton buttonO = new JButton("O");
	
	protected JButton buttonA = new JButton("A");	protected JButton buttonS = new JButton("S");
	protected JButton buttonD = new JButton("D");	protected JButton buttonF = new JButton("F");
	protected JButton buttonG = new JButton("G");	protected JButton buttonH = new JButton("H");
	protected JButton buttonJ = new JButton("J");	protected JButton buttonK = new JButton("K");

	protected JButton buttonP = new JButton("P");	protected JButton buttonY = new JButton("Y");
	protected JButton buttonX = new JButton("X");	protected JButton buttonC = new JButton("C");
	protected JButton buttonV = new JButton("V");	protected JButton buttonB = new JButton("B");
	protected JButton buttonN = new JButton("N");	protected JButton buttonM = new JButton("M");
	protected JButton buttonL = new JButton("L");	
	
	// Ausgabe
	
	protected JPanel outputPanel = new JPanel();
	
	protected JTextArea output = new JTextArea(5,52);	// für die erzeugte Ausgabe
	
	// Ende Komponenten
	
	protected Character[] KEY_LAYOUT = {
		'Q','W','E','R','T','Z','U','I','O',
		'A','S','D','F','G','H','J','K','L',
		'P','Y','X','C','V','B','N','M'
	};	
	
	public Machine( Hardware hardware ) {
		enigmaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		enigmaFrame.setLayout(new BorderLayout());
		
		// Hardware
		this.hardware = hardware;
		
		// Rotoren	
		
		SpinnerModel alphaOne = new SpinnerNumberModel(1,1,26,1);
		SpinnerModel alphaTwo = new SpinnerNumberModel(1,1,26,1);
		SpinnerModel alphaThree = new SpinnerNumberModel(1,1,26,1);
		
		rotorOne.setModel(alphaOne);
		rotorOne.addMouseWheelListener(this);		
		rotorTwo.setModel(alphaTwo);
		rotorTwo.addMouseWheelListener(this);
		rotorThree.setModel(alphaThree);
		rotorThree.addMouseWheelListener(this);
		
		rotorPanel.add(rotorOne);
		rotorPanel.add(rotorTwo);
		rotorPanel.add(rotorThree);
		
		rotorOneBox.setSelectedIndex( 0 );
		rotorTwoBox.setSelectedIndex( 1 );
		rotorThreeBox.setSelectedIndex( 2 );
		rotorPanel.add( rotorOneBox );
		rotorPanel.add( rotorTwoBox );
		rotorPanel.add( rotorThreeBox );
		
		enigmaFrame.add(rotorPanel, BorderLayout.WEST);
		
		// Lichter
		
		boardPanel.setLayout(new GridLayout(2,1));
		
		lightsPanel.setLayout(new GridLayout(3,8));
		
		lightList[0] = lightQ;		lightList[1] = lightW;
		lightList[2] = lightE;		lightList[3] = lightR;
		lightList[4] = lightT;		lightList[5] = lightZ;
		lightList[6] = lightU;		lightList[7] = lightI;
		lightList[8] = lightO;		lightList[9] = lightA;
		lightList[10] = lightS;		lightList[11] = lightD;
		lightList[12] = lightF;		lightList[13] = lightG;
		lightList[14] = lightH;		lightList[15] = lightJ;
		lightList[16] = lightK;		lightList[17] = lightL;
		lightList[18] = lightP;		lightList[19] = lightY;
		lightList[20] = lightX;		lightList[21] = lightC;
		lightList[22] = lightV;		lightList[23] = lightB;
		lightList[24] = lightN;		lightList[25] = lightM;
		
		for (int i = 0; i < lightList.length; i++) {
			lightList[i].add(new JLabel(KEY_LAYOUT[i].toString()));
			lightsPanel.add(lightList[i]);
		}
		
		boardPanel.add(lightsPanel);
		
		
		// Knöpfe auf Panel
		
		buttonPanel.setLayout(new GridLayout(3,8));
		
		buttonList[0] = buttonQ;		buttonList[1] = buttonW;
		buttonList[2] = buttonE;		buttonList[3] = buttonR;
		buttonList[4] = buttonT;		buttonList[5] = buttonZ;
		buttonList[6] = buttonU;		buttonList[7] = buttonI;
		buttonList[8] = buttonO;		buttonList[9] = buttonA;
		buttonList[10] = buttonS;		buttonList[11] = buttonD;
		buttonList[12] = buttonF;		buttonList[13] = buttonG;
		buttonList[14] = buttonH;		buttonList[15] = buttonJ;
		buttonList[16] = buttonK;		buttonList[17] = buttonL;
		buttonList[18] = buttonP;		buttonList[19] = buttonY;
		buttonList[20] = buttonX;		buttonList[21] = buttonC;
		buttonList[22] = buttonV;		buttonList[23] = buttonB;
		buttonList[24] = buttonN;		buttonList[25] = buttonM;
		
		for (int i = 0; i < 26; i++) {
			buttonList[i].addMouseListener(new LetterListener());
			buttonPanel.add(buttonList[i]);
		}
		
		boardPanel.add(buttonPanel);
		enigmaFrame.add(boardPanel, BorderLayout.CENTER);
		
		
		// Ausgabe

		output.setEditable(false);
		
		outputPanel.add(output);
		enigmaFrame.add(outputPanel, BorderLayout.NORTH);

		enigmaFrame.pack();
		enigmaFrame.setVisible(true);
	}
	
	public void mouseWheelMoved(MouseWheelEvent event) {
		if (event.getComponent() instanceof JSpinner) {
			JSpinner source = (JSpinner) event.getComponent();

			if (event.getWheelRotation() > 0) {
				if (source.getPreviousValue() != null)
					source.setValue(source.getPreviousValue());
				else
					source.setValue(26);
			}
			else {
				if (source.getNextValue() != null)
					source.setValue(source.getNextValue());
				else
					source.setValue(1);
			}
		}
	}
	
	/**
	 * Methode, um das Licht eines Buchstaben aufleuchten zu lassen, genauer
	 * gesagt, das JPanel, in dem er liegt.
	 * 
	 * @param c		Buchstabe, der aufleuchten soll
	 */
	public void lightLetter(char c) {
		Character ch = Character.toUpperCase(c);
		
		for (int i = 0; i < lightList.length; i++) {
//			System.out.println(KEY_LAYOUT[i]+" vs. "+ch+" = "+KEY_LAYOUT[i].equals(ch));
			if (KEY_LAYOUT[i].equals(ch)) {
				lightList[i].setBackground(Color.GREEN);
				output.setText(output.getText()+ch);
			}
			else {
				lightList[i].setBackground(null);
			}
		}			
	}
	
	public class LetterListener extends MouseAdapter {
		
        @Override
		public void mousePressed(MouseEvent event) {
			
			// Methode, um die richtige Rotoren einzusetzen
			hardware.setRotorI( hardware.getRotorChoice().get( (String)rotorOneBox.getSelectedItem() ) );
			hardware.setRotorII( hardware.getRotorChoice().get( (String)rotorTwoBox.getSelectedItem() ) );
			hardware.setRotorIII( hardware.getRotorChoice().get( (String)rotorThreeBox.getSelectedItem() ) );
		
			
			// Methode, um die aktuelle Rotor Position auszulesen
			readRotor();	
			
			// Methode, um den Buchstaben zu codieren
			lightLetter( hardware.crypt( ((JButton)( event.getSource())).getActionCommand().charAt( 0 ) ) );
			
			// Methode, um die Rotoren der Oberfläche auf den aktuellen Wert zu setzen
			rotorUpdate();
		}
		
		public void mouseReleased(MouseEvent arg0) {
			// Lösche alle Lichter
			lightLetter('0');
		}
		
		// Aktualisieren der Rotoren der Oberfläche
		private void rotorUpdate(){
			rotorOne.setValue( hardware.getRotorI().getRotorPos() );
			rotorTwo.setValue( hardware.getRotorII().getRotorPos() );
			rotorThree.setValue( hardware.getRotorIII().getRotorPos() );
		}
		
		// Auslesen der Rotoren der Oberfläche
		private void readRotor(){
			hardware.getRotorI().setRotorPos( (Integer)( rotorOne.getValue() ) );
			hardware.getRotorII().setRotorPos( (Integer)( rotorTwo.getValue() ) );
			hardware.getRotorIII().setRotorPos( (Integer)( rotorThree.getValue() ) );
		
		}
	}
}

