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

import de.fhbingen.igru2.enigma.logic.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author om
 */
public class PlugBoardFrame extends javax.swing.JFrame {
    private Plugboard plugboard;

    private final Character[] KEY_LAYOUT = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
        'V', 'W', 'X', 'Y', 'Z'};

    /** Creates new form PlugBoardFrame */
    public PlugBoardFrame(JFrame parent, Plugboard plugboard) {
        initComponents();
        this.parent = parent;
        this.plugboard = plugboard;
        fromSelect.setModel(new DefaultComboBoxModel(KEY_LAYOUT));
        fillDestSelect();
        
        HashMap<Character, Character> connections = plugboard.getConnections();
        DefaultListModel listModel = new DefaultListModel();
        for (Map.Entry connection : connections.entrySet()) {
            listModel.addElement(connection.getKey() + " - " + connection.getValue());
        }
        connectList.setModel(listModel);

    }
    
    public Plugboard getPlugboard() {
        return plugboard;
    }

    private void fillDestSelect() {
        int pos = Arrays.binarySearch(KEY_LAYOUT, (Character)fromSelect.getSelectedItem());
        Character[] modAlpha = new Character[KEY_LAYOUT.length-1];
        int cnt = 0;
        for (int i=0; i<KEY_LAYOUT.length; i++) {
            if (pos != i) {
                modAlpha[cnt] = KEY_LAYOUT[i];
                cnt++;
            }
        }
        destSelect.setModel(new DefaultComboBoxModel(modAlpha));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fromSelect = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        destSelect = new javax.swing.JComboBox();
        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        connectList = new javax.swing.JList();
        delButton = new javax.swing.JButton();
        delButton1 = new javax.swing.JButton();

        setTitle("Steckerbrett");
        setModalExclusionType(null);
        setResizable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Steckerbrett"));

        jLabel1.setText("Verbindung von");

        fromSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromSelectActionPerformed(evt);
            }
        });

        jLabel2.setText("bis");

        addButton.setText("Hinzufügen");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(connectList);

        delButton.setText("Entfernen");
        delButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonActionPerformed(evt);
            }
        });

        delButton1.setLabel("Schließen");
        delButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fromSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(delButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delButton1)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fromSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(destSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delButton1)
                    .addComponent(delButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if (fromSelect.getModel().getSelectedItem() == null || destSelect.getModel().getSelectedItem() == null) {
            return;
        }

        if (!plugboard.addConnection((Character)fromSelect.getModel().getSelectedItem(), (Character)destSelect.getModel().getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Ungültige Verbindung", "Warnung", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultListModel model = new DefaultListModel();
       
        for (int i=0; i<connectList.getModel().getSize(); i++) {
            model.addElement(connectList.getModel().getElementAt(i));
        }
        
        model.addElement(fromSelect.getModel().getSelectedItem() + " - " + destSelect.getModel().getSelectedItem());

        connectList.setModel(model);
    }//GEN-LAST:event_addButtonActionPerformed

    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        if (connectList.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Sie haben keine Verbindung gewählt", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!plugboard.removeConnection(connectList.getSelectedValue().toString().charAt(0))) {
            JOptionPane.showMessageDialog(this, "Verbindung konnte nicht gelöscht werden", "Warnung", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DefaultListModel model = new DefaultListModel();
        for (int i=0; i<connectList.getModel().getSize(); i++) {
            if (i != connectList.getSelectedIndex()) {
                model.addElement(connectList.getModel().getElementAt(i));
            }
        }
        connectList.setModel(model);
    }//GEN-LAST:event_delButtonActionPerformed

    private void fromSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromSelectActionPerformed
        fillDestSelect();
    }//GEN-LAST:event_fromSelectActionPerformed

    private void delButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButton1ActionPerformed
        setVisible(false);
        if (parent != null)
            parent.setEnabled(true);
    }//GEN-LAST:event_delButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JList connectList;
    private javax.swing.JButton delButton;
    private javax.swing.JButton delButton1;
    private javax.swing.JComboBox destSelect;
    private javax.swing.JComboBox fromSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private JFrame parent;
}
