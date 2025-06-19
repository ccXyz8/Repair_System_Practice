package org.example.repairsystembackend.repairman.entity;

import lombok.Data;

@Data
public class Repairman{
    private int id;
    private String username;
    private String password;
    private int phone;
    private String department;
    private Double rating ;

}
