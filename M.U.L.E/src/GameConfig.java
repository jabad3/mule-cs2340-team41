/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class configures the game: difficulty, maptype, and number of players
 * @author Erica Pramer
 * @version 1
 */
public class GameConfig extends javax.swing.JPanel {

    /**
     * Initializes components on the JPanel
     * 
     * @param L 
     *            Action listener passed in by deck panel to facilitate
     *            movement between panels via next button
     */
    public GameConfig(ActionListener L) {
        initComponents();
        nextButton.addActionListener(L);
    }

    /**
     * A getter for the next button on the jpanel
     * 
     * @return L the next button
     */
	public JButton getNextButton()
	{
		return nextButton;
	}
    /**
     * Instantiates all instance data, adds all jcomponents to the panel
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        difficultyBox = new javax.swing.JComboBox();
        mapTypeBox = new javax.swing.JComboBox();
        playerNumBox = new javax.swing.JComboBox();
        nextButton = new javax.swing.JButton();
        difficultyText = new javax.swing.JLabel();
        mapTypeText = new javax.swing.JLabel();
        playerNumText = new javax.swing.JLabel();

        difficultyBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Beginner", "Standard", "Tournament" }));

        mapTypeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        playerNumBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        nextButton.setLabel("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        difficultyText.setText("Difficulty:");
    	

        mapTypeText.setText("Map Type:");

        playerNumText.setText("Number of Players:");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            	.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nextButton)
                        .addGap(14, 14, 14))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(playerNumText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(difficultyText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mapTypeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(difficultyBox, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mapTypeBox, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerNumBox, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                        .addGap(121, 121, 121))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(difficultyBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(difficultyText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(mapTypeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mapTypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(playerNumText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerNumBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(nextButton)
                .addContainerGap())
        );
    }

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration
    private javax.swing.JComboBox difficultyBox;
    private javax.swing.JLabel difficultyText;
    private javax.swing.JComboBox mapTypeBox;
    private javax.swing.JLabel mapTypeText;
    private javax.swing.JButton nextButton;
    private javax.swing.JComboBox playerNumBox;
    private javax.swing.JLabel playerNumText;
}
