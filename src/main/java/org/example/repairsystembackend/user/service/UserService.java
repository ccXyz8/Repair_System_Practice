package org.example.repairsystembackend.user.service;


import io.minio.GetObjectResponse;
import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    User findByUsername(String username);

    User findByUserId(int userId);

    int modifyUser(User user);

    int applyRepair(Repair repair);

    List<Repair> trackRepair(int userId);

    List<Repair> getRepairs();

    int feedBack(int orderId, String endTime, String feedback);

    boolean uploadImage(MultipartFile file);

    void getImage(OutputStream stream, String filename) throws Exception;
}
