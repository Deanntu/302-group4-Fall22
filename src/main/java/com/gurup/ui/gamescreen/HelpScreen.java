package com.gurup.ui.gamescreen;

import com.gurup.ui.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.gurup.ui.ImageLoader.*;

public class HelpScreen extends JFrame implements ActionListener {

    private final Container helpScreenContainer = getContentPane();
    private final JButton backButton = new JButton("BACK");
    private boolean showBack = false;

    public HelpScreen() {
        helpScreenContainer.setLayout(null);
        setSizeandAdd();
    }

    private void setSizeandAdd() {

        backButton.setBounds(10, 10, 100, 30);
        helpScreenContainer.add(backButton);
        backButton.addActionListener(this);

        // text field
        JLabel helpLabel = new JLabel("OYUN");
        helpScreenContainer.add(helpLabel);
        helpLabel.setBounds(475, 50, 200, 30);



        JLabel alienLabel = new JLabel("Aliens");
        helpScreenContainer.add(alienLabel);
        alienLabel.setBounds(50, 100, 200, 30);

        JLabel alienLabel0 = new JLabel("Aliens are your enemy.");
        helpScreenContainer.add(alienLabel0);
        alienLabel0.setBounds(50, 120, 200, 30);

        JLabel alienLabel1 = new JLabel("Time Wasting");
        helpScreenContainer.add(alienLabel1);
        alienLabel1.setBounds(50, 150, 200, 30);

        JLabel alienLabel1Image = new JLabel();
        alienLabel1Image.setIcon(new ImageIcon(time_wasting_alien_image.getScaledInstance(30,30,30)));
        helpScreenContainer.add(alienLabel1Image);
        alienLabel1Image.setBounds(50,180,200,30);

        JLabel alienLabel1Text = new JLabel("This aliens makes you spend time faster.");
        helpScreenContainer.add(alienLabel1Text);
        alienLabel1Text.setBounds(80, 180, 300, 30);

        JLabel alienLabel2 = new JLabel("Blind");
        helpScreenContainer.add(alienLabel2);
        alienLabel2.setBounds(50, 250, 200, 30);

        JLabel alienLabel2Image = new JLabel();
        alienLabel2Image.setIcon(new ImageIcon(blind_alien_image.getScaledInstance(30,30,30)));
        helpScreenContainer.add(alienLabel2Image);
        alienLabel2Image.setBounds(50,280,200,30);

        JLabel alienLabel2Text = new JLabel("This aliens moves randomly.");
        helpScreenContainer.add(alienLabel2Text);
        alienLabel2Text.setBounds(80, 280, 300, 30);

        JLabel alienLabel3 = new JLabel("Shooter");
        helpScreenContainer.add(alienLabel3);
        alienLabel3.setBounds(50, 350, 200, 30);

        JLabel alienLabel3Image = new JLabel();
        alienLabel3Image.setIcon(new ImageIcon(shooter_alien_image.getScaledInstance(30,30,30)));
        helpScreenContainer.add(alienLabel3Image);
        alienLabel3Image.setBounds(50,380,200,30);

        JLabel alienLabel3Text = new JLabel("This aliens shoots you.");
        helpScreenContainer.add(alienLabel3Text);
        alienLabel3Text.setBounds(80, 380, 300, 30);




        JLabel powerUpsLabel = new JLabel("Power Ups");
        helpScreenContainer.add(powerUpsLabel);
        powerUpsLabel.setBounds(475, 100, 200, 30);

        JLabel powerUpsLabel0 = new JLabel("Power Ups are your friends.");
        helpScreenContainer.add(powerUpsLabel0);
        powerUpsLabel0.setBounds(475, 120, 200, 30);

        JLabel powerUpsLabel1 = new JLabel("Bottle");
        helpScreenContainer.add(powerUpsLabel1);
        powerUpsLabel1.setBounds(475, 150, 200, 30);

        JLabel powerUpsLabel1Image = new JLabel();
        powerUpsLabel1Image.setIcon(new ImageIcon(plastic_bottle_image.getScaledInstance(30,30,30)));
        helpScreenContainer.add(powerUpsLabel1Image);
        powerUpsLabel1Image.setBounds(475,180,200,30);

        JLabel powerUpsLabel2 = new JLabel("Health");
        helpScreenContainer.add(powerUpsLabel2);
        powerUpsLabel2.setBounds(475, 250, 200, 30);

        JLabel powerUpsLabel2Image = new JLabel();
        powerUpsLabel2Image.setIcon(new ImageIcon(health_image.getScaledInstance(30,30,30)));
        helpScreenContainer.add(powerUpsLabel2Image);
        powerUpsLabel2Image.setBounds(475,280,200,30);

        JLabel powerUpsLabel3 = new JLabel("Time");
        helpScreenContainer.add(powerUpsLabel3);
        powerUpsLabel3.setBounds(475, 350, 200, 30);

        JLabel powerUpsLabel3Image = new JLabel();
        powerUpsLabel3Image.setIcon(new ImageIcon(time_image.getScaledInstance(30,30,30)));
        helpScreenContainer.add(powerUpsLabel3Image);
        powerUpsLabel3Image.setBounds(475,380,200,30);

        JLabel powerUpsLabel4 = new JLabel("Vest");
        helpScreenContainer.add(powerUpsLabel4);
        powerUpsLabel4.setBounds(475, 450, 200, 30);

        JLabel powerUpsLabel4Image = new JLabel();
        powerUpsLabel4Image.setIcon(new ImageIcon(vest_image.getScaledInstance(30,30,30)));
        helpScreenContainer.add(powerUpsLabel4Image);
        powerUpsLabel4Image.setBounds(475,480,200,30);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            showBack = true;
        }

    }

    public boolean showBackPressed() {
        return showBack;
    }
}
