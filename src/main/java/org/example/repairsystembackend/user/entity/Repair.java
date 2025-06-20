package org.example.repairsystembackend.user.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Repair {

    private int id;
    private int userId;
    private int repairmanId;
    private String description;
    private String deviceInfo;
    private String picture;
    private String startTime;
    private String endTime;
    private String status;
    private String feedback;

}
