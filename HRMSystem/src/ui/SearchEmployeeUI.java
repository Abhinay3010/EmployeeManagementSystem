package ui;

import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;

public class SearchEmployeeUI extends JFrame {
    JTextField inputField;
    JTextArea resultArea;

    public SearchEmployeeUI() {
        setTitle("🔍 Search Employee");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        inputField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setEditable(false);

        JPanel top = new JPanel();
        top.add(new JLabel("ID or Name:"));
        top.add(inputField);
        top.add(searchBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchBtn.addActionListener(e -> {
            String input = inputField.getText().trim();
            Employee emp = input.matches("\\d+") ?
                    new EmployeeService().getEmployeeById(Integer.parseInt(input)) :
                    new EmployeeService().getEmployeeByName(input);

            if (emp != null) {
                resultArea.setText("ID: " + emp.getId() + "\nName: " + emp.getName() +
                    "\nDept: " + emp.getDepartment() +
                    "\nMonthly Salary: ₹" + (emp.getSalary() / 12) +
                    "\nJoining: " + emp.getJoiningDate() +
                    "\nLeaving: " + (emp.getLeavingDate() != null ? emp.getLeavingDate() : "N/A"));
            } else {
                resultArea.setText("Employee Not Found.");
            }
        });

        setVisible(true);
    }
}