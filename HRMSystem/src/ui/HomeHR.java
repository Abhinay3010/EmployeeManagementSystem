package ui;

import javax.swing.*;
import java.awt.*;

public class HomeHR extends JFrame {

    public HomeHR() {
        setTitle("🏢 HR Dashboard");
        setLayout(new GridLayout(3, 2, 30, 30));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font btnFont = new Font("Arial", Font.BOLD, 18);

        JButton addBtn = new JButton("➕ Add Employee");
        JButton viewBtn = new JButton("👁 View Employees");
        JButton editBtn = new JButton("✏ Edit Employee");
        JButton deleteBtn = new JButton("🗑 Remove Employee");
        JButton searchBtn = new JButton("🔍 Search Employee");
        JButton payslipBtn = new JButton("📥 Download Payslip");

        JButton[] buttons = { addBtn, viewBtn, editBtn, deleteBtn, searchBtn, payslipBtn };
        for (JButton b : buttons) {
            b.setFont(btnFont);
            add(b);
        }

        addBtn.addActionListener(e -> new AddEmployeeUI());
        viewBtn.addActionListener(e -> new ViewEmployeeUI());
        editBtn.addActionListener(e -> new EditEmployeeUI());
        deleteBtn.addActionListener(e -> new RemoveEmployeeUI());
        searchBtn.addActionListener(e -> new SearchEmployeeUI());
        payslipBtn.addActionListener(e -> new DownloadPayslipUI());

        setVisible(true);
    }
}