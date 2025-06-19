package org.example.repairsystembackend.user.entity;

import lombok.Data;

@Data
public class ImageInfo {
    private String filename;
    private String url;
    private String bucketName;
    private int userId;



}
