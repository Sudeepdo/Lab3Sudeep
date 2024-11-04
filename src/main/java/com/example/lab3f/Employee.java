package com.example.lab3f;
public class Employee {
    private int employee_id;
    private String employee_name;
    private String address;
    private String salary;

    public Employee(int employee_id, String employee_name, String address, String salary) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.address = address;
        this.salary = salary;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}