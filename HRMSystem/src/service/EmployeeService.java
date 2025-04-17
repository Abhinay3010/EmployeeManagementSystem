package service;

import db.DBConnection;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeService {

    public boolean addEmployee(Employee emp) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO employees (name, department, salary, joining_date) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());
            ps.setDate(4, new java.sql.Date(emp.getJoiningDate().getTime()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                list.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("salary"),
                    rs.getDate("joining_date"),
                    rs.getDate("leaving_date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Employee getEmployeeById(int id) {
        Employee emp = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("salary"),
                    rs.getDate("joining_date"),
                    rs.getDate("leaving_date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    public Employee getEmployeeByName(String name) {
        Employee emp = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("salary"),
                    rs.getDate("joining_date"),
                    rs.getDate("leaving_date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    public boolean updateEmployee(Employee emp) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE employees SET name=?, department=?, salary=?, joining_date=?, leaving_date=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());
            ps.setDate(4, new java.sql.Date(emp.getJoiningDate().getTime()));
            ps.setDate(5, emp.getLeavingDate() != null ? new java.sql.Date(emp.getLeavingDate().getTime()) : null);
            ps.setInt(6, emp.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeEmployee(Employee emp) {
        try (Connection conn = DBConnection.getConnection()) {
            String backup = "INSERT INTO removed_employees VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement backupStmt = conn.prepareStatement(backup);
            backupStmt.setInt(1, emp.getId());
            backupStmt.setString(2, emp.getName());
            backupStmt.setString(3, emp.getDepartment());
            backupStmt.setDouble(4, emp.getSalary());
            backupStmt.setDate(5, new java.sql.Date(emp.getJoiningDate().getTime()));
            backupStmt.setDate(6, new java.sql.Date(System.currentTimeMillis()));
            backupStmt.executeUpdate();

            String delete = "DELETE FROM employees WHERE id=?";
            PreparedStatement deleteStmt = conn.prepareStatement(delete);
            deleteStmt.setInt(1, emp.getId());
            return deleteStmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}