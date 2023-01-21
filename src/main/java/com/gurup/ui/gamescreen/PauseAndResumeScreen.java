package com.gurup.ui.gamescreen;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PauseAndResumeScreen extends JFrame implements ActionListener {

    private final Container pauseAndResumeScreen = getContentPane();

    private final JButton resumeButton = new JButton("RESUME");
    private final JButton helpButton = new JButton("HELP");
    private final JButton exitButton = new JButton("EXİT");

    private boolean showHelp = false;
    private boolean showExit = false;
    private boolean continueGame = false;

    public PauseAndResumeScreen() {
        pauseAndResumeScreen.setLayout(null);
        setSizeandAdd();
    }

    private void setSizeandAdd() {
        resumeButton.setBounds(125, 100, 100, 30);
        helpButton.setBounds(125, 150, 100, 30);
        exitButton.setBounds(125, 200, 100, 30);

        pauseAndResumeScreen.add(resumeButton);
        pauseAndResumeScreen.add(helpButton);
        pauseAndResumeScreen.add(exitButton);

        resumeButton.addActionListener(this);
        helpButton.addActionListener(this);
        exitButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resumeButton) {
            continueGame = true;
        }
        if (e.getSource() == helpButton) {
            showHelp = true;
        }
        if (e.getSource() == exitButton) {
            showExit = true;
            System.exit(0);
        }

    }

    public boolean isContinueGame() {
        return continueGame;
    }

    public void setContinueGame(boolean flag) {
        continueGame = flag;
    }

    public boolean showHelpPressed() {
        return showHelp;
    }

    public void setShowHelp(Boolean flag) {
        showHelp = flag;
    }

    public boolean showExitPressed() {
        return showExit;
    }

    public void setShowExit(Boolean flag) {
        showExit = flag;
    }

}
