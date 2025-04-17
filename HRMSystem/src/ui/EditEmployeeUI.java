package ui;

import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class EditEmployeeUI extends JFrame {
    JTextField idField, nameField, deptField, salaryField, joinDateField;
    JButton loadButton, updateButton;
    Employee emp;

    public EditEmployeeUI() {
        setTitle("✏ Edit Employee");
        setLayout(new GridLayout(6, 2, 10, 10));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        idField = new JTextField();
        nameField = new JTextField();
        deptField = new JTextField();
        salaryField = new JTextField();
        joinDateField = new JTextField();

        loadButton = new JButton("Load");
        updateButton = new JButton("Update");

        add(new JLabel("Enter ID:")); add(idField);
        add(loadButton); add(new JLabel());

        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Department:")); add(deptField);
        add(new JLabel("Annual Salary:")); add(salaryField);
        add(new JLabel("Joining Date:")); add(joinDateField);
        add(updateButton);

        nameField.setEnabled(false); deptField.setEnabled(false);
        salaryField.setEnabled(false); joinDateField.setEnabled(false);
        updateButton.setEnabled(false);

        loadButton.addActionListener(e -> {
            emp = new EmployeeService().getEmployeeById(Integer.parseInt(idField.getText()));
            if (emp != null) {
                nameField.setText(emp.getName());
                deptField.setText(emp.getDepartment());
                salaryField.setText(String.valueOf(emp.getSalary()));
                joinDateField.setText(emp.getJoiningDate().toString());

                nameField.setEnabled(true); deptField.setEnabled(true);
                salaryField.setEnabled(true); joinDateField.setEnabled(true);
                updateButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Not Found");
            }
        });

        updateButton.addActionListener(e -> {
            emp.setName(nameField.getText());
            emp.setDepartment(deptField.getText());
            emp.setSalary(Double.parseDouble(salaryField.getText()));
            emp.setJoiningDate(Date.valueOf(joinDateField.getText()));

            boolean updated = new EmployeeService().updateEmployee(emp);
            JOptionPane.showMessageDialog(this, updated ? "Updated" : "Failed");
            dispose();
        });

        setVisible(true);
    }
}