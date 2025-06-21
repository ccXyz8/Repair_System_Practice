package org.example.repairsystembackend.repairman.entity;

import lombok.Data;

@Data
public class Repairman{
    private int repairmanId;
    private String username;
    private String password;
    private int phone;
    private String department;
    private int permission;
}
