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

        JLabel helpPNG = new JLabel();
        helpPNG.setIcon(new ImageIcon(help.getScaledInstance(900,800,600)));
        helpScreenContainer.add(helpPNG);
        helpPNG.setBounds(40,0,1000,800);

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
