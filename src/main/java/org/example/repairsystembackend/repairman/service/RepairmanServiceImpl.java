package org.example.repairsystembackend.repairman.service;


import org.example.repairsystembackend.repairman.mapper.RepairmanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairmanServiceImpl implements RepairmanService {

    private final RepairmanMapper repairmanMapper;

    @Autowired
    public RepairmanServiceImpl(RepairmanMapper repairmanMapper) {
        this.repairmanMapper = repairmanMapper;
    }

}
