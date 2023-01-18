package com.gurup.ui.gamescreen;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.gurup.domain.saver.SaverType;

public class LoginScreen extends JFrame implements ActionListener {

    private final Container loginContainer = getContentPane();
    private final JLabel usernameLabel = new JLabel("Username");
    private final JLabel mailLabel = new JLabel("Mail");
    private final JLabel pswrdLabel = new JLabel("Password");
    private final ButtonGroup buttonGroup;
    private final JTextField userTextField = new JTextField("");
    private final JTextField mailTextField = new JTextField("");
    private final JPasswordField passwordField = new JPasswordField("");
    private final JButton loginButton = new JButton("LOGIN");
    private final JButton registerButton = new JButton("REGISTER");
    private final JButton withoutLoginButton = new JButton("PLAY WITHOUT LOGIN");
    private final JCheckBox showPasswordCheckbox;


    private final JRadioButton databaseRadioButton;
    private final JRadioButton fileRadioButton;
    private final JLabel saveToWhereLabel;


    private boolean loginPressed = false;
    private boolean registerPressed = false;
    private boolean withoutLoginPressed = false;

    public LoginScreen() {
        loginContainer.setLayout(null);
        showPasswordCheckbox = new JCheckBox("Show Password");
        databaseRadioButton = new JRadioButton("Database");
        fileRadioButton = new JRadioButton("File");
        saveToWhereLabel = new JLabel("Save Game to: ");
        setSizeandAdd();
        buttonGroup = new ButtonGroup();
        buttonGroup.add(databaseRadioButton);
        buttonGroup.add(fileRadioButton);
    }

    private void setSizeandAdd() {
        saveToWhereLabel.setBounds(50, 50, 100, 30);
        databaseRadioButton.setBounds(150, 50, 100, 30);
        fileRadioButton.setBounds(250, 50, 100, 30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(databaseRadioButton);
        buttonGroup.add(fileRadioButton);
        usernameLabel.setBounds(50, 100, 100, 30);
        userTextField.setBounds(125, 100, 175, 30);
        mailLabel.setBounds(50, 150, 100, 30);
        mailTextField.setBounds(125, 150, 175, 30);
        pswrdLabel.setBounds(50, 200, 100, 30);
        passwordField.setBounds(125, 200, 175, 30);
        showPasswordCheckbox.setBounds(125, 250, 150, 30);
        registerButton.setBounds(50, 300, 100, 30);
        loginButton.setBounds(200, 300, 100, 30);
        withoutLoginButton.setBounds(50, 340, 250, 30);

        loginContainer.add(usernameLabel);
        loginContainer.add(mailLabel);
        loginContainer.add(pswrdLabel);
        loginContainer.add(userTextField);
        loginContainer.add(mailTextField);
        loginContainer.add(passwordField);
        loginContainer.add(showPasswordCheckbox);
        loginContainer.add(registerButton);
        loginContainer.add(loginButton);
        loginContainer.add(withoutLoginButton);
        loginContainer.add(saveToWhereLabel);
        loginContainer.add(databaseRadioButton);
        loginContainer.add(fileRadioButton);

        registerButton.addActionListener(this);
        loginButton.addActionListener(this);
        showPasswordCheckbox.addActionListener(this);
        withoutLoginButton.addActionListener(this);
        databaseRadioButton.addActionListener(this);
        fileRadioButton.addActionListener(this);

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
                passwordField.setEchoChar('●');
            }

        }
        if (e.getSource() == withoutLoginButton) {
            withoutLoginPressed = true;
        }
    }

    public SaverType getSaverType() {
        if (databaseRadioButton.isSelected()) {
            return SaverType.DATABASE;
        } else if (fileRadioButton.isSelected()) {
            return SaverType.TXT;
        }
        return SaverType.NOTINITIALIZED;

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

    public boolean isWithoutLoginPressed() {
        return withoutLoginPressed;
    }

    public void setWithoutLoginPressed(boolean withoutLoginPressed) {
        this.withoutLoginPressed = withoutLoginPressed;
    }
}
