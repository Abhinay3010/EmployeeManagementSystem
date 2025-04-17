package ui;

import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadPayslipUI extends JFrame {

    JTextField idField;
    JButton downloadButton;
    JTextArea output;

    public DownloadPayslipUI() {
        setTitle("📄 Download Employee Payslip");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter Employee ID:"));
        idField = new JTextField(10);
        topPanel.add(idField);

        downloadButton = new JButton("Download Payslip");
        topPanel.add(downloadButton);

        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);

        downloadButton.addActionListener(e -> downloadPayslip());

        setVisible(true);
    }

    void downloadPayslip() {
        String input = idField.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid employee ID.");
            return;
        }

        try {
            int id = Integer.parseInt(input);
            EmployeeService service = new EmployeeService();
            Employee emp = service.getEmployeeById(id);

            if (emp == null) {
                output.setText("❌ Employee not found.");
                return;
            }

            String month = new SimpleDateFormat("MMMM yyyy").format(new Date());
            double monthlySalary = emp.getSalary() / 12;

            String fileName = "Payslip_" + emp.getName().replaceAll("\\s+", "") + "_" + new SimpleDateFormat("yyyyMM").format(new Date()) + ".txt";
            FileWriter writer = new FileWriter(fileName);

            String payslip = "------ Employee Payslip ------\n"
                    + "Month: " + month + "\n"
                    + "Employee ID: " + emp.getId() + "\n"
                    + "Name: " + emp.getName() + "\n"
                    + "Department: " + emp.getDepartment() + "\n"
                    + "Monthly Salary: ₹" + monthlySalary + "\n"
                    + "------------------------------\n"
                    + "Note: This is a system-generated payslip.";

            writer.write(payslip);
            writer.close();

            output.setText("✅ Payslip downloaded as '" + fileName + "'\n\n" + payslip);

        } catch (Exception ex) {
            output.setText("⚠ Error: " + ex.getMessage());
        }
    }
}