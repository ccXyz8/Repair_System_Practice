package org.example.repairsystembackend.user.entity;

import java.time.LocalDateTime;

public class Repair {

    private int id;
    private int userId;
    private int repairmanId;
    private String description;
    private String deviceInfo;
    private String pictureUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String feedback;

}
