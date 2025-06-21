package org.example.repairsystembackend.repairman.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.example.repairsystembackend.repairman.entity.Repairman;
import org.example.repairsystembackend.repairman.service.RepairmanService;
import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.utils.view.JsonBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class RepairmanController {

    private final RepairmanService repairmanService;

    @Autowired
    public RepairmanController(final RepairmanService repairmanService) {
        this.repairmanService = repairmanService;
    }

    //通过id查询自身
    @RequestMapping("/anonymous/findRepairmanById")
    public JsonBody findRepairmanById(int repairmanId) {
        Repairman repairman = repairmanService.getRepairmanById(repairmanId);
        if (repairman == null) {
            return JsonBody.failed(400,"failed").add("message", "用户没有找到");
        }
        return JsonBody.success(200,"success").add("repairman", repairman);
    }

    //通过username查找自身
    @RequestMapping("/anonymous/findRepairmanByUsername")
    public JsonBody findRepairmanByUsername(String username) {
        Repairman repairman = repairmanService.getRepairmanByUsername(username);
        if (repairman == null) {
            return JsonBody.failed(400,"failed").add("message", "用户没有找到");
        }
        return JsonBody.success(200,"success").add("repairman", repairman);
    }

    //传输repairman整个类
    @RequestMapping("/anonymous/register")
    public JsonBody register(@RequestBody Repairman repairman) {
        repairman.setPermission(3);
        int result = repairmanService.registerRepairman(repairman);
        if (result == 1) {
            return JsonBody.success(200,"success");
        }
        return JsonBody.failed(400,"failed").add("message","注册失败");
    }

    //传输repairman来修改自身信息
    @RequestMapping("/repairman/updateRepairman")
    public JsonBody updateRepairman(@RequestBody Repairman repairman) {
        int result = repairmanService.updateRepairman(repairman);
        if (result == 1) {
            return JsonBody.success(200,"success");
        }
        return JsonBody.failed(400,"failed").add("message", "修改失败");
    }

    //只能获取状态为“未修理”的报修单
    //调用这个前端需要再调用getImage获取图片
    @RequestMapping("/repairman/getAllRepairs")
    public JsonBody getAllRepairs() {
        List<Repair> repairs = repairmanService.getAllRepairs();
        if (repairs==null||repairs.isEmpty()) {
            return JsonBody.success(200,"zero").add("repairs", repairs);
        }
        return JsonBody.success(200,"success").add("repairs", repairs);
    }

    //获取维修工自己接受的报修单
    //调用这个前端需要再调用getImage获取图片
    @RequestMapping("/repairman/getRepairsByRepairmanId")
    public JsonBody getRepairsByRepairmanId(int repairmanId) {
        List<Repair> repairs = repairmanService.getRepairsByRepairmanId(repairmanId);
        if (repairs == null||repairs.isEmpty()) {
            return JsonBody.success(200,"zero").add("repairs", repairs);
        }
        return JsonBody.success(200,"success").add("repair", repairs);
    }

    //获取保修单上的图片
    @RequestMapping("/repairman/getImage")
    public void getImage(String fileName, HttpServletResponse response) throws Exception {
        ServletOutputStream stream = response.getOutputStream();
        repairmanService.getImage(stream,fileName);
    }

    //接受一个报修单并将状态改为修理中
    @RequestMapping("/repairman/acceptRepair")
    public JsonBody acceptRepair(int repairId,int repairmanId) {
        int result = repairmanService.acceptRepair(repairId, repairmanId);
        if (result == 1) {
            return JsonBody.success(200,"success");
        }
        return JsonBody.failed(400,"failed").add("message","接单失败");
    }

    //完成一个报修，并将状态改为修理结束
    @RequestMapping("/repairman/complexRepair")
    public JsonBody complexRepair(int repairId){
        LocalDateTime endTime = LocalDateTime.now();
        int result = repairmanService.complexRepair(repairId,endTime);
        if (result == 1) {
            return JsonBody.success(200,"success");
        }
        return JsonBody.failed(400,"failed").add("message","报修未能完成");
    }

}
