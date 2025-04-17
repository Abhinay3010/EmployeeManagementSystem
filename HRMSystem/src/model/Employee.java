package model;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary; // Annual salary
    private Date joiningDate;
    private Date leavingDate;

    public Employee(int id, String name, String department, double salary, Date joiningDate, Date leavingDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.leavingDate = leavingDate;
    }

    public Employee(String name, String department, double salary, Date joiningDate) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public Date getJoiningDate() { return joiningDate; }
    public Date getLeavingDate() { return leavingDate; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setJoiningDate(Date joiningDate) { this.joiningDate = joiningDate; }
    public void setLeavingDate(Date leavingDate) { this.leavingDate = leavingDate; }
}