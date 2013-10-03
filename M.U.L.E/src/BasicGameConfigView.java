/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class configures the game: difficulty, maptype, and number of players
 * All choices are displayed to the user using JComboBox, and the user presses
 * a "Next" button when they are finished.
 * 
 * @author Erica Pramer
 * @version 1
 */
public class BasicGameConfigView extends GameConfigView {

    private JComboBox difficultyBox;
    private JLabel difficultyText;
    private JComboBox mapTypeBox;
    private JLabel mapTypeText;
    private JComboBox playerNumBox;
    private JLabel playerNumText;
    private JButton nextButton;
    
    /**
     * Initializes components on the JPanel
     * 
     * @param L 
     *            Action listener passed in by deck panel to facilitate
     *            movement between panels via next button
     */
    public BasicGameConfigView() {
        initComponents();
    }

    /**
     * Instantiates all instance data, adds all jcomponents to the panel
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        difficultyBox = new JComboBox();
        mapTypeBox = new JComboBox();
        playerNumBox = new JComboBox();
        nextButton = new JButton("Next");
        difficultyText = new JLabel("Difficulty:");
        mapTypeText = new JLabel("Map Type:");
        playerNumText = new JLabel("Number of Players:");

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

    @Override
    public void setDifficultyChoices(Difficulty[] difficulties) {
        difficultyBox.setModel(new DefaultComboBoxModel(difficulties));
    }

    @Override
    public void setMapTypeChoices(String[] mapChoices) {
        mapTypeBox.setModel(new DefaultComboBoxModel(mapChoices));
    }

    @Override
    public void setPlayerCountChoices(Integer[] playerCountChoices) {
        playerNumBox.setModel(new DefaultComboBoxModel(playerCountChoices));
        
    }

    @Override
    public void addFinishedListener(ActionListener finishedListener) {
        nextButton.addActionListener(finishedListener);
    }

    @Override
    public Difficulty getSelectedDifficulty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSelectedMapType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSelectedPlayerCount() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
