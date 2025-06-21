package org.example.repairsystembackend.repairman.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.repairsystembackend.repairman.entity.Repairman;
import org.example.repairsystembackend.user.entity.Repair;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RepairmanMapper {

    Repairman getRepairmanByRepairmanId(int repairmanId);

    Repairman getRepairmanByUsername(String username);

    int insertRepairman(Repairman repairman);

    int updateRepairman(Repairman repairman);

    List<Repair> getAllRepairs();

    List<Repair> getRepairsByRepairmanId(int repairmanId);

    int updateRepair(int repairId,int repairmanId);

    int updateRepairStatus(int repairId, LocalDateTime endTime);


}
