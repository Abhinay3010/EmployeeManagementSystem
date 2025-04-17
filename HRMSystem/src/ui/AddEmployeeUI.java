package ui;

import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class AddEmployeeUI extends JFrame {
    JTextField nameField, deptField, salaryField, joinDateField;
    JButton saveButton;

    public AddEmployeeUI() {
        setTitle("➕ Add Employee");
        setLayout(new GridLayout(5, 2, 15, 15));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        nameField = new JTextField();
        deptField = new JTextField();
        salaryField = new JTextField();
        joinDateField = new JTextField("YYYY-MM-DD");

        saveButton = new JButton("Save");

        add(new JLabel("Full Name:")); add(nameField);
        add(new JLabel("Department:")); add(deptField);
        add(new JLabel("Annual Salary:")); add(salaryField);
        add(new JLabel("Joining Date:")); add(joinDateField);
        add(saveButton);

        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String dept = deptField.getText().trim();
                double salary = Double.parseDouble(salaryField.getText().trim());
                Date joinDate = Date.valueOf(joinDateField.getText().trim());

                Employee emp = new Employee(name, dept, salary, joinDate);
                boolean result = new EmployeeService().addEmployee(emp);
                JOptionPane.showMessageDialog(this, result ? "Employee Added" : "Failed to Add");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}