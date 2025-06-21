package org.example.repairsystembackend.repairman.service;


import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import org.apache.commons.compress.utils.IOUtils;
import org.example.repairsystembackend.repairman.entity.Repairman;
import org.example.repairsystembackend.repairman.mapper.RepairmanMapper;
import org.example.repairsystembackend.user.entity.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepairmanServiceImpl implements RepairmanService {

    private final RepairmanMapper repairmanMapper;
    private final MinioClient client;
    private final String bucketName="repair";

    @Autowired
    public RepairmanServiceImpl(RepairmanMapper repairmanMapper, MinioClient client) {
        this.repairmanMapper = repairmanMapper;
        this.client = client;
    }

    @Override
    public Repairman getRepairmanById(int repairmanId) {
        return repairmanMapper.getRepairmanByRepairmanId(repairmanId);
    }

    @Override
    public Repairman getRepairmanByUsername(String username) {
        return repairmanMapper.getRepairmanByUsername(username);
    }

    @Override
    public int registerRepairman(Repairman repairman) {
        return repairmanMapper.insertRepairman(repairman);
    }

    @Override
    public int updateRepairman(Repairman repairman) {
        return repairmanMapper.updateRepairman(repairman);
    }

    @Override
    public List<Repair> getAllRepairs() {
        return repairmanMapper.getAllRepairs();
    }

    @Override
    public List<Repair> getRepairsByRepairmanId(int repairmanId) {
        return repairmanMapper.getRepairsByRepairmanId(repairmanId);
    }

    @Override
    public int acceptRepair(int repairId, int repairmanId) {
        return repairmanMapper.updateRepair(repairId, repairmanId);
    }

    @Override
    public int complexRepair(int repairId, LocalDateTime endTime) {
        return repairmanMapper.updateRepairStatus(repairId,endTime);
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
