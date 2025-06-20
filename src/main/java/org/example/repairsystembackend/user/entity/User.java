package org.example.repairsystembackend.user.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private int phone;
    private String department;
    private int permission;

}
