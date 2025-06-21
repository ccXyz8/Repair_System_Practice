package org.example.repairsystembackend.user.entity.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class RepairRequest {

    private int userId;
    private int repairmanId;
    private String description;
    private String deviceInfo;
    private MultipartFile file;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRepairmanId() {
        return repairmanId;
    }

    public void setRepairmanId(int repairmanId) {
        this.repairmanId = repairmanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
