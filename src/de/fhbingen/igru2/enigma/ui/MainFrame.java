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
//import de.fhbingen.igru2.enigma.*;
//import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author om
 */
public class MainFrame extends javax.swing.JFrame {
    private Hardware hardware;
    private JTextField[] lightList = new JTextField[26];
    private JButton[] buttonList = new JButton[26];
    private final Character[] KEY_LAYOUT = {
		'Q','W','E','R','T','Z','U','I','O',
		'A','S','D','F','G','H','J','K','L',
		'P','Y','X','C','V','B','N','M'
	};
    private String[] rotors = { "CylinderI", "CylinderII", "CylinderIII", "CylinderIV", "CylinderV" };

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();

        setPreferredSize(new Dimension(540,610));
        pack();
        
        // Eigene UI-Initialisierung
        rotorOne.setModel(new SpinnerNumberModel(1,1,26,1));
        rotorTwo.setModel(new SpinnerNumberModel(1,1,26,1));
        rotorThree.setModel(new SpinnerNumberModel(1,1,26,1));

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
		}

                // Eigenständige EventQueue, die auf Tastatureingaben wartet und
                //  diese als Eingabe für die Enigma wertet
                Toolkit.getDefaultToolkit().getSystemEventQueue().push(
                new EventQueue() {
            @Override
                    protected void dispatchEvent(AWTEvent event) {
                        if (event instanceof KeyEvent) {
                            KeyEvent keyEvent = (KeyEvent) event;
                            if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                                // Methode, um die richtige Rotoren einzusetzen
                                hardware.setRotorI( hardware.getRotorChoice().get( rotorOneBox.getSelectedItem() ) );
                                hardware.setRotorII( hardware.getRotorChoice().get( rotorTwoBox.getSelectedItem() ) );
                                hardware.setRotorIII( hardware.getRotorChoice().get( rotorThreeBox.getSelectedItem() ) );

                                // Methode, um die aktuelle Rotor Position auszulesen
                                readRotor();

                                // Methode, um den Buchstaben zu codieren
                                Character ch = keyEvent.getKeyChar();
                                ch = Character.toUpperCase(ch);

                                if ((ch >= 'A') && (ch <= 'Z')) {
                                    Character code = hardware.crypt( ( ch ) );

                                    lightLetter(code);
                                    jTextArea1.append(code.toString());

                                    // Methode, um die Rotoren der Oberfläche auf den aktuellen Wert zu setzen
                                    rotorUpdate();
                                }
                            }
                            if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
                                // Lösche alle Lichter
                                lightLetter('0');
                            }
                        }


                        super.dispatchEvent(event);
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
                });
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
				//output.setText(output.getText()+ch);
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
			hardware.setRotorI( hardware.getRotorChoice().get( rotorOneBox.getSelectedItem() ) );
			hardware.setRotorII( hardware.getRotorChoice().get( rotorTwoBox.getSelectedItem() ) );
			hardware.setRotorIII( hardware.getRotorChoice().get( rotorThreeBox.getSelectedItem() ) );

			// Methode, um die aktuelle Rotor Position auszulesen
			readRotor();

			// Methode, um den Buchstaben zu codieren
                        Character code = hardware.crypt( ((JButton)( event.getSource())).getActionCommand().charAt( 0 ) );

			lightLetter(code);
                        jTextArea1.append(code.toString());

			// Methode, um die Rotoren der Oberfläche auf den aktuellen Wert zu setzen
			rotorUpdate();
		}

        @Override
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

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public void setRotorTwo(JSpinner rotorTwo) {
        this.rotorTwo = rotorTwo;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonQ = new javax.swing.JButton();
        buttonW = new javax.swing.JButton();
        buttonE = new javax.swing.JButton();
        buttonR = new javax.swing.JButton();
        buttonI = new javax.swing.JButton();
        buttonZ = new javax.swing.JButton();
        buttonU = new javax.swing.JButton();
        buttonT = new javax.swing.JButton();
        buttonA = new javax.swing.JButton();
        buttonO = new javax.swing.JButton();
        buttonP = new javax.swing.JButton();
        buttonD = new javax.swing.JButton();
        buttonF = new javax.swing.JButton();
        buttonG = new javax.swing.JButton();
        buttonH = new javax.swing.JButton();
        buttonJ = new javax.swing.JButton();
        buttonK = new javax.swing.JButton();
        buttonL = new javax.swing.JButton();
        buttonY = new javax.swing.JButton();
        buttonC = new javax.swing.JButton();
        buttonV = new javax.swing.JButton();
        buttonX = new javax.swing.JButton();
        buttonN = new javax.swing.JButton();
        buttonB = new javax.swing.JButton();
        buttonM = new javax.swing.JButton();
        buttonS = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rotorOne = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        rotorTwo = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        rotorThree = new javax.swing.JSpinner();
        rotorOneBox = new javax.swing.JComboBox();
        rotorTwoBox = new javax.swing.JComboBox();
        rotorThreeBox = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        lightQ = new javax.swing.JTextField();
        lightW = new javax.swing.JTextField();
        lightE = new javax.swing.JTextField();
        lightR = new javax.swing.JTextField();
        lightT = new javax.swing.JTextField();
        lightZ = new javax.swing.JTextField();
        lightU = new javax.swing.JTextField();
        lightI = new javax.swing.JTextField();
        lightO = new javax.swing.JTextField();
        lightP = new javax.swing.JTextField();
        lightA = new javax.swing.JTextField();
        lightS = new javax.swing.JTextField();
        lightD = new javax.swing.JTextField();
        lightF = new javax.swing.JTextField();
        lightG = new javax.swing.JTextField();
        lightH = new javax.swing.JTextField();
        lightJ = new javax.swing.JTextField();
        lightK = new javax.swing.JTextField();
        lightL = new javax.swing.JTextField();
        lightY = new javax.swing.JTextField();
        lightX = new javax.swing.JTextField();
        lightC = new javax.swing.JTextField();
        lightV = new javax.swing.JTextField();
        lightB = new javax.swing.JTextField();
        lightN = new javax.swing.JTextField();
        lightM = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        plugBoardConfMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        faqMenuItem = new javax.swing.JMenuItem();
        infoMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ENIGMA-Simulator");
        setFocusable(false);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Eingabefeld"));

        buttonQ.setText("Q");

        buttonW.setText("W");

        buttonE.setText("E");

        buttonR.setText("R");

        buttonI.setText("I");

        buttonZ.setText("Z");

        buttonU.setText("U");

        buttonT.setText("T");

        buttonA.setText("A");

        buttonO.setText("O");

        buttonP.setText("P");

        buttonD.setText("D");

        buttonF.setText("F");

        buttonG.setText("G");

        buttonH.setText("H");

        buttonJ.setText("J");

        buttonK.setText("K");

        buttonL.setText("L");

        buttonY.setText("Y");

        buttonC.setText("C");

        buttonV.setText("V");

        buttonX.setText("X");

        buttonN.setText("N");

        buttonB.setText("B");

        buttonM.setText("M");

        buttonS.setText("S");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(buttonQ)
                        .addGap(4, 4, 4)
                        .addComponent(buttonW)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonZ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonO)
                        .addGap(6, 6, 6)
                        .addComponent(buttonP))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(buttonY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonM))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buttonS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonG)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonJ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonL)))))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonQ)
                    .addComponent(buttonW)
                    .addComponent(buttonE)
                    .addComponent(buttonR)
                    .addComponent(buttonT)
                    .addComponent(buttonZ)
                    .addComponent(buttonU)
                    .addComponent(buttonI)
                    .addComponent(buttonO)
                    .addComponent(buttonP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonA)
                    .addComponent(buttonD)
                    .addComponent(buttonF)
                    .addComponent(buttonG)
                    .addComponent(buttonH)
                    .addComponent(buttonJ)
                    .addComponent(buttonK)
                    .addComponent(buttonL)
                    .addComponent(buttonS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonY)
                    .addComponent(buttonX)
                    .addComponent(buttonC)
                    .addComponent(buttonV)
                    .addComponent(buttonB)
                    .addComponent(buttonN)
                    .addComponent(buttonM))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Walzen"));

        jLabel1.setText("Walze I");

        rotorOne.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                MainFrame.this.mouseWheelMoved(evt);
            }
        });

        jLabel2.setText("Walze II");

        rotorTwo.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                MainFrame.this.mouseWheelMoved(evt);
            }
        });

        jLabel3.setText("Walze III");

        rotorThree.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                MainFrame.this.mouseWheelMoved(evt);
            }
        });

        rotorOneBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CylinderI", "CylinderII", "CylinderIII", "CylinderIV", "CylinderV" }));

        rotorTwoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CylinderI", "CylinderII", "CylinderIII", "CylinderIV", "CylinderV" }));
        rotorTwoBox.setSelectedIndex(1);

        rotorThreeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CylinderI", "CylinderII", "CylinderIII", "CylinderIV", "CylinderV" }));
        rotorThreeBox.setSelectedIndex(2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rotorOneBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotorOne, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rotorTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotorTwoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(rotorThree, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotorThreeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotorThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rotorTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(rotorOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotorTwoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotorOneBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotorThreeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lampenfeld"));

        lightQ.setEditable(false);
        lightQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightQ.setText("Q");

        lightW.setEditable(false);
        lightW.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightW.setText("W");

        lightE.setEditable(false);
        lightE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightE.setText("E");

        lightR.setEditable(false);
        lightR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightR.setText("R");

        lightT.setEditable(false);
        lightT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightT.setText("T");

        lightZ.setEditable(false);
        lightZ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightZ.setText("Z");

        lightU.setEditable(false);
        lightU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightU.setText("U");

        lightI.setEditable(false);
        lightI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightI.setText("I");

        lightO.setEditable(false);
        lightO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightO.setText("O");

        lightP.setEditable(false);
        lightP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightP.setText("P");

        lightA.setEditable(false);
        lightA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightA.setText("A");

        lightS.setEditable(false);
        lightS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightS.setText("S");

        lightD.setEditable(false);
        lightD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightD.setText("D");

        lightF.setEditable(false);
        lightF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightF.setText("F");

        lightG.setEditable(false);
        lightG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightG.setText("G");

        lightH.setEditable(false);
        lightH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightH.setText("H");

        lightJ.setEditable(false);
        lightJ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightJ.setText("J");

        lightK.setEditable(false);
        lightK.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightK.setText("K");

        lightL.setEditable(false);
        lightL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightL.setText("L");

        lightY.setEditable(false);
        lightY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightY.setText("Y");

        lightX.setEditable(false);
        lightX.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightX.setText("X");

        lightC.setEditable(false);
        lightC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightC.setText("C");

        lightV.setEditable(false);
        lightV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightV.setText("V");

        lightB.setEditable(false);
        lightB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightB.setText("B");

        lightN.setEditable(false);
        lightN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightN.setText("N");

        lightM.setEditable(false);
        lightM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lightM.setText("M");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lightQ, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightW, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightE, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightR, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightZ, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightU, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightI, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightO, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lightA, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightS, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightG, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightJ, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightK, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lightY, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightC, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightM, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lightQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lightA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lightX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lightM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Chiffre"));
        jPanel4.setMinimumSize(new java.awt.Dimension(0, 0));

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Datei");

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Beenden");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Chiffre");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Kopieren");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Löschen");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Steckerbrett");

        plugBoardConfMenuItem.setText("Konfigurieren");
        plugBoardConfMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plugBoardConfMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(plugBoardConfMenuItem);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Hilfe");

        faqMenuItem.setText("Anleitung");
        faqMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faqMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(faqMenuItem);

        infoMenuItem.setText("Über");
        infoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(infoMenuItem);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.getAccessibleContext().setAccessibleName("Ausgabe");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void plugBoardConfMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plugBoardConfMenuItemActionPerformed
        PlugBoardFrame confDlg = new PlugBoardFrame(this, hardware.getPlugboard());
        this.setEnabled(false);
        confDlg.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                setEnabled(true);
            }
        });
 		confDlg.setAlwaysOnTop(true);
        confDlg.setVisible(true);
    }//GEN-LAST:event_plugBoardConfMenuItemActionPerformed

    private void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_mouseWheelMoved
        if (evt.getComponent() instanceof JSpinner) {
			JSpinner source = (JSpinner) evt.getComponent();

			if (evt.getWheelRotation() > 0) {
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
    }//GEN-LAST:event_mouseWheelMoved

    private void infoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoMenuItemActionPerformed
        JOptionPane.showMessageDialog(this, "<html><b>ENIGMA-Simulator - Grundlagen der Informatik 2 - FH Bingen</b><br/><br/>" +
                                            "Eine Java-Simulation der Verschlüsselungsmaschine Enigma.<br/><br/>" +
                                            "Copyright (C) 2009, Oliver Martin, Jonas Kleemann und Andreas Trepczik<br/><br/>"+
                                            "THE BEER-WARE LICENSE (Revision 42):<br/>"+
                                            "Oliver Martin, Jonas Kleemann and Andreas Trepczik wrote this file.<br/>" +
                                            "As long as you retain this notice you can do whatever you want with this stuff.<br/>" +
                                            "If we meet some day, and you think this stuff is worth it, <br/>" +
                                            "you can buy us a beer in return." +
                                            "</html>", "Über", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_infoMenuItemActionPerformed

    private void faqMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faqMenuItemActionPerformed
        JOptionPane.showMessageDialog(this, "<html>1. Walzen und Startposition wählen<br/>2. Steckbrett konfigurieren<br/>3. Text eingeben </html>", "Anleitung", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_faqMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jTextArea1.setText("");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jTextArea1.copy();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        jTextArea1.requestFocus();
    }//GEN-LAST:event_formWindowGainedFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonA;
    private javax.swing.JButton buttonB;
    private javax.swing.JButton buttonC;
    private javax.swing.JButton buttonD;
    private javax.swing.JButton buttonE;
    private javax.swing.JButton buttonF;
    private javax.swing.JButton buttonG;
    private javax.swing.JButton buttonH;
    private javax.swing.JButton buttonI;
    private javax.swing.JButton buttonJ;
    private javax.swing.JButton buttonK;
    private javax.swing.JButton buttonL;
    private javax.swing.JButton buttonM;
    private javax.swing.JButton buttonN;
    private javax.swing.JButton buttonO;
    private javax.swing.JButton buttonP;
    private javax.swing.JButton buttonQ;
    private javax.swing.JButton buttonR;
    private javax.swing.JButton buttonS;
    private javax.swing.JButton buttonT;
    private javax.swing.JButton buttonU;
    private javax.swing.JButton buttonV;
    private javax.swing.JButton buttonW;
    private javax.swing.JButton buttonX;
    private javax.swing.JButton buttonY;
    private javax.swing.JButton buttonZ;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem faqMenuItem;
    private javax.swing.JMenuItem infoMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField lightA;
    private javax.swing.JTextField lightB;
    private javax.swing.JTextField lightC;
    private javax.swing.JTextField lightD;
    private javax.swing.JTextField lightE;
    private javax.swing.JTextField lightF;
    private javax.swing.JTextField lightG;
    private javax.swing.JTextField lightH;
    private javax.swing.JTextField lightI;
    private javax.swing.JTextField lightJ;
    private javax.swing.JTextField lightK;
    private javax.swing.JTextField lightL;
    private javax.swing.JTextField lightM;
    private javax.swing.JTextField lightN;
    private javax.swing.JTextField lightO;
    private javax.swing.JTextField lightP;
    private javax.swing.JTextField lightQ;
    private javax.swing.JTextField lightR;
    private javax.swing.JTextField lightS;
    private javax.swing.JTextField lightT;
    private javax.swing.JTextField lightU;
    private javax.swing.JTextField lightV;
    private javax.swing.JTextField lightW;
    private javax.swing.JTextField lightX;
    private javax.swing.JTextField lightY;
    private javax.swing.JTextField lightZ;
    private javax.swing.JMenuItem plugBoardConfMenuItem;
    private javax.swing.JSpinner rotorOne;
    private javax.swing.JComboBox rotorOneBox;
    private javax.swing.JSpinner rotorThree;
    private javax.swing.JComboBox rotorThreeBox;
    private javax.swing.JSpinner rotorTwo;
    private javax.swing.JComboBox rotorTwoBox;
    // End of variables declaration//GEN-END:variables

}
