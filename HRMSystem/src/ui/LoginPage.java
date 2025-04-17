package ui;

import model.User;
import service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginPage() {
        setTitle("👤 HRM System - Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 20, 20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        add(new JLabel("Username:")); add(usernameField);
        add(new JLabel("Password:")); add(passwordField);
        add(new JLabel("")); add(loginButton);

        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        User user = new AuthService().login(username, password);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
            return;
        }

        JOptionPane.showMessageDialog(this, "Welcome, " + user.getUsername() + "!");
        dispose();
        if (user.getRole().equalsIgnoreCase("hr")) {
            new HomeHR();
        } else {
            new HomeEmployee(user.getUsername());
        }
    }
}