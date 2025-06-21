package org.example.repairsystembackend.user.entity.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RepairRequest {

    private int userId;
    private int repairmanId;
    private String description;
    private String deviceInfo;
    private MultipartFile file;
}
