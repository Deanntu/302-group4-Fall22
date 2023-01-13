package com.gurup.ui.gamescreen;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenuScreen extends JFrame implements ActionListener {
	private final Container mainMenuContainer = getContentPane();
	private JButton playButton = new JButton("PLAY");
	private JButton loadButton = new JButton("LOAD");
	private JButton helpButton = new JButton("HELP");
	private JButton exitButton = new JButton("EXIT");

	private boolean showHelp = false;
	private boolean showExit = false;
	private boolean showPlay = false;
  private boolean showLoad = false;

	public MainMenuScreen() {
		mainMenuContainer.setLayout(null);
		setSizeandAdd();
	}

	private void setSizeandAdd() {
		playButton.setBounds(125, 100, 100, 30);
		loadButton.setBounds(125, 150, 100, 30);
		helpButton.setBounds(125, 200, 100, 30);
		exitButton.setBounds(125, 250, 100, 30);

		mainMenuContainer.add(playButton);
		mainMenuContainer.add(loadButton);
		mainMenuContainer.add(helpButton);
		mainMenuContainer.add(exitButton);

		playButton.addActionListener(this);
		loadButton.addActionListener(this);
		helpButton.addActionListener(this);
		exitButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == playButton) {
			showPlay = true;
		}
		if (e.getSource() == loadButton) {
		    showLoad = true;
		}
		if (e.getSource() == helpButton) {
			showHelp = true;
		}
		if (e.getSource() == exitButton) {
			showExit = true;
			System.exit(0);
		}

	}

	public boolean showHelpPressed() {
		return showHelp;
	}

	public void setShowHelp(Boolean flag) {
		showHelp = flag;
	};

	public boolean showPlayPressed() {
		return showPlay;
	}

	public void setShowPlay(Boolean flag) {
		showPlay = flag;
	};

	public boolean showExitPressed() {
		return showExit;
	}

	public void setShowExit(Boolean flag) {
		showExit = flag;
	}

    public boolean showLoadPressed() {
        return showLoad;
    }

    public void setShowLoad(boolean showLoad) {
        this.showLoad = showLoad;
    };
}
