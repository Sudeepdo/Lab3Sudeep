package com.example.lab3f;
public class Admin {
    private int  admin_id;
    private String admin_name;
    private String email;
    private String password;

    public Admin(int admin_id, String admin_name, String email, String password) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.email = email;
        this.password = password;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
