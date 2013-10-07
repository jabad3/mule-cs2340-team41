/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class lets a user choose difficulty, maptype, and number of players
 * 
 * All choices are displayed to the user using JComboBox, and the user presses
 * a "Next" button when they are finished.
 * 
 * @author Erica Pramer
 * @version 1
 */
public class BasicGameConfigView extends GameConfigView {

    private JComboBox<String> difficultyBox;
    private JLabel difficultyText;
    private JComboBox<String> mapTypeBox;
    private JLabel mapTypeText;
    private JComboBox<Integer> playerNumBox;
    private JLabel playerNumText;
    private JButton nextButton;
    
    /**
     * Initializes components on the JPanel
     */
    public BasicGameConfigView() {
        initComponents();
    }

    /**
     * Instantiates all instance data, adds all jcomponents to the panel
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        difficultyBox = new JComboBox<>();
        mapTypeBox = new JComboBox<>();
        playerNumBox = new JComboBox<>();
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
        String[] diffNames = new String[difficulties.length];
        for (int i = 0; i < difficulties.length; i++)
            diffNames[i] = difficulties[i].toString();
        difficultyBox.setModel(new DefaultComboBoxModel<String>(diffNames));
    }

    @Override
    public void setMapTypeChoices(String[] mapChoices) {
        mapTypeBox.setModel(new DefaultComboBoxModel<String>(mapChoices));
    }

    @Override
    public void setPlayerCountChoices(Integer[] playerCountChoices) {
        playerNumBox.setModel(new DefaultComboBoxModel<Integer>(playerCountChoices));
        
    }

    @Override
    public void addFinishedListener(ActionListener finishedListener) {
        nextButton.addActionListener(finishedListener);
    }

    @Override
    public Difficulty getSelectedDifficulty() {
        String diffName = (String) difficultyBox.getSelectedItem();
        diffName = diffName.toUpperCase();
        Difficulty selectedDiff = Difficulty.valueOf(diffName);
        return selectedDiff;
    }

    @Override
    public String getSelectedMapType() {
        String selectedMapType = (String) mapTypeBox.getSelectedItem();
        return selectedMapType;
    }

    @Override
    public int getSelectedPlayerCount() {
        int selectedCount = (Integer) playerNumBox.getSelectedItem();
        return selectedCount;
    }
    
}
