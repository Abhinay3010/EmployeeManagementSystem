package ui;

import javax.swing.*;
import java.awt.*;

public class HomeEmployee extends JFrame {

    public HomeEmployee(String username) {
        setTitle("👨‍💼 Employee Dashboard");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea infoArea = new JTextArea("Welcome, " + username + "!\n\nNote: Payslips are provided by HR.\nContact HR for salary slips or updates.");
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        add(new JScrollPane(infoArea), BorderLayout.CENTER);
        setVisible(true);
    }
}