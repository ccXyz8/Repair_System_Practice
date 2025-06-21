package org.example.repairsystembackend.repairman.service;

import org.example.repairsystembackend.repairman.entity.Repairman;
import org.example.repairsystembackend.user.entity.Repair;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

public interface RepairmanService {

    Repairman getRepairmanById(int repairmanId);

    Repairman getRepairmanByUsername(String username);

    int registerRepairman(Repairman repairman);

    int updateRepairman(Repairman repairman);

    List<Repair> getAllRepairs();

    List<Repair> getRepairsByRepairmanId(int repairmanId);

    int acceptRepair(int repairId,int repairmanId);

    int complexRepair(int repairId, LocalDateTime endTime);

    void getImage(OutputStream stream, String filename) throws Exception;
}
