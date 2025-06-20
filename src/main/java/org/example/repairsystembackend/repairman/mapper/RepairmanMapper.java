package org.example.repairsystembackend.repairman.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.repairsystembackend.repairman.entity.Repairman;
import org.example.repairsystembackend.user.entity.Repair;

import java.util.List;

@Mapper
public interface RepairmanMapper {

    Repairman getRepairmanByRepairmanId(int repairmanId);

    int updateRepairman(Repairman repairman);

    List<Repair> getAllRepairs();

    List<Repair> getRepairsByRepairId(int repairId);

    int updateRepair(int repairId,int repairmanId);

    int updateRepairStatus(int repairId,String status);

}
