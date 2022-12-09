package com.gurup.ui.gamescreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame implements ActionListener {

	private final Container loginContainer = getContentPane();
	private final JLabel usernameLabel = new JLabel("Username");
	private final JLabel mailLabel = new JLabel("Mail");
	private final JLabel pswrdLabel = new JLabel("Password");
	private JTextField userTextField = new JTextField("");
	private JTextField mailTextField = new JTextField("");
	private JPasswordField passwordField = new JPasswordField("");
	private JButton loginButton = new JButton("LOGIN");
	private JButton registerButton = new JButton("REGISTER");
	private JCheckBox showPasswordCheckbox;
	private boolean loginPressed = false;
	private boolean registerPressed = false;

	public LoginScreen() {
		loginContainer.setLayout(null);
		showPasswordCheckbox = new JCheckBox("Show Password");
		setSizeandAdd();
	}

	private void setSizeandAdd() {
		usernameLabel.setBounds(50, 100, 100, 30);
		userTextField.setBounds(125, 100, 175, 30);
		mailLabel.setBounds(50, 150, 100, 30);
		mailTextField.setBounds(125, 150, 175, 30);
		pswrdLabel.setBounds(50, 200, 100, 30);
		passwordField.setBounds(125, 200, 175, 30);
		showPasswordCheckbox.setBounds(125, 250, 150, 30);
		registerButton.setBounds(50, 300, 100, 30);
		loginButton.setBounds(200, 300, 100, 30);

		loginContainer.add(usernameLabel);
		loginContainer.add(mailLabel);
		loginContainer.add(pswrdLabel);
		loginContainer.add(userTextField);
		loginContainer.add(mailTextField);
		loginContainer.add(passwordField);
		loginContainer.add(showPasswordCheckbox);
		loginContainer.add(registerButton);
		loginContainer.add(loginButton);

		registerButton.addActionListener(this);
		loginButton.addActionListener(this);
		showPasswordCheckbox.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == registerButton) {
			registerPressed = true;

		}
		if (e.getSource() == loginButton) {
			loginPressed = true;
		}

		if (e.getSource() == showPasswordCheckbox) {
			if (showPasswordCheckbox.isSelected()) {
				passwordField.setEchoChar('\u0000');
			} else {
				passwordField.setEchoChar('\u25CF');
			}

		}

	}

	public boolean isLoginPressed() {
		return loginPressed;
	}

	public void setLoginPressed(Boolean flag) {
		loginPressed = flag;
	}

	public void setRegisterPressed(Boolean flag) {

		registerPressed = flag;
	}

	public boolean isRegisterPressed() {
		return registerPressed;
	}

	public String getEnteredUsermame() {
		return userTextField.getText();
	}

	public String getEnteredMail() {
		return mailTextField.getText();
	}

	public String getEnteredPassword() {
		return String.valueOf(passwordField.getPassword());
	}

}
