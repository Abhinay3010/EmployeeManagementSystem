package ui;

import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewEmployeeUI extends JFrame {
    JTextArea displayArea;

    public ViewEmployeeUI() {
        setTitle("👁 View Employees");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        showData();

        setVisible(true);
    }

    void showData() {
        ArrayList<Employee> list = new EmployeeService().getAllEmployees();
        StringBuilder sb = new StringBuilder("EMPLOYEE LIST\n\n");
        for (Employee emp : list) {
            sb.append("ID: ").append(emp.getId())
              .append(" | Name: ").append(emp.getName())
              .append(" | Dept: ").append(emp.getDepartment())
              .append(" | Monthly Salary: ₹").append(emp.getSalary() / 12)
              .append(" | Joining: ").append(emp.getJoiningDate())
              .append(" | Leaving: ").append(emp.getLeavingDate() != null ? emp.getLeavingDate() : "N/A")
              .append("\n");
        }
        displayArea.setText(sb.toString());
    }
}