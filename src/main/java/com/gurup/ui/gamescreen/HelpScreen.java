package com.gurup.ui.gamescreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.gurup.ui.ImageLoader.*;

public class HelpScreen extends JFrame implements ActionListener {

    private final Container helpScreenContainer = getContentPane();
    private JButton backButton = new JButton("BACK");
    private boolean showBack = false;

    public HelpScreen(){
        helpScreenContainer.setLayout(null);
        setSizeandAdd();
    }

    private void setSizeandAdd() {

        backButton.setBounds(10, 10, 100, 30);
        helpScreenContainer.add(backButton);
        backButton.addActionListener(this);

        // text field
        JLabel helpLabel = new JLabel("Help");
        helpScreenContainer.add(helpLabel);
        helpLabel.setBounds(50, 50, 200, 30);

        // png file
        JLabel pngFile1 = new JLabel(new ImageIcon(help_screen1));

        pngFile1.setVerticalAlignment(JLabel.CENTER);
        pngFile1.setBounds(100,100,1000,1000);
        //pngFile1.resize(helpScreenContainer.getWidth(),helpScreenContainer.getHeight());
        helpScreenContainer.add(pngFile1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            showBack = true;
        }

    }

    public boolean showBackPressed() {
        return showBack;
    }
}
