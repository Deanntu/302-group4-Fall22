package com.gurup.ui.gamescreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel helpLabel = new JLabel("Help");
        helpScreenContainer.add(helpLabel);

        backButton.addActionListener(this);


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
