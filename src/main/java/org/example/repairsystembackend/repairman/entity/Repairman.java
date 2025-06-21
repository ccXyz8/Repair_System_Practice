package org.example.repairsystembackend.repairman.entity;

public class Repairman{
    private int repairmanId;
    private String username;
    private String password;
    private int phone;
    private String department;
    private int permission;

    public int getRepairmanId() {
        return repairmanId;
    }

    public void setRepairmanId(int repairmanId) {
        this.repairmanId = repairmanId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
