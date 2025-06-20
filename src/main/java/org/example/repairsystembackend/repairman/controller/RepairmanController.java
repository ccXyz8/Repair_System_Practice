package org.example.repairsystembackend.repairman.controller;

import org.example.repairsystembackend.repairman.service.RepairmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class RepairmanController {

    private final RepairmanService repairmanService;

    @Autowired
    public RepairmanController(final RepairmanService repairmanService) {
        this.repairmanService = repairmanService;
    }


}
