package org.example.repairsystembackend.user.service;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.user.entity.User;
import org.example.repairsystembackend.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final String bucketName="repair";
    private final MinioClient client;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(MinioClient client, UserMapper userMapper) {
        this.client = client;
        this.userMapper = userMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User findByUserId(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int modifyUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int applyRepair(Repair repair) {
        return userMapper.insertRepair(repair);
    }

    @Override
    public List<Repair> trackRepair(int userId) {
        return userMapper.getRepairByUserId(userId);
    }

    @Override
    public List<Repair> getRepairs() {
        return userMapper.getRepairs();
    }

    @Override
    public int feedBack(int repairId, LocalDateTime endTime, String feedback) {
        return userMapper.updateRepair(repairId, endTime, feedback);
    }

    public boolean uploadImage(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(originalFilename)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return true;
        }catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            return false;
        }
    }

    @Override
    public void getImage(OutputStream stream, String filename)throws Exception {
        GetObjectResponse object = client.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .build());
        stream.write(object.readAllBytes());
    }
}
