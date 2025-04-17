package ui;

import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;

public class RemoveEmployeeUI extends JFrame {
    JTextField idField;
    JButton removeButton;

    public RemoveEmployeeUI() {
        setTitle("🗑 Remove Employee");
        setLayout(new GridLayout(2, 2, 10, 10));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        idField = new JTextField();
        removeButton = new JButton("Remove");

        add(new JLabel("Enter Employee ID to Remove:"));
        add(idField);
        add(removeButton);

        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Employee emp = new EmployeeService().getEmployeeById(id);
                if (emp != null) {
                    boolean removed = new EmployeeService().removeEmployee(emp);
                    JOptionPane.showMessageDialog(this, removed ? "Employee Removed (Backed up)" : "Failed to remove.");
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        setVisible(true);
    }
}