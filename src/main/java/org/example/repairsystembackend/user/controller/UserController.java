package org.example.repairsystembackend.user.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.user.entity.User;
import org.example.repairsystembackend.user.entity.request.RepairRequest;
import org.example.repairsystembackend.user.service.UserService;
import org.example.repairsystembackend.utils.view.JsonBody;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //通过username查找自身
    @RequestMapping("/anonymous/findUserByUsername")
    public JsonBody findUserByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return JsonBody.failed(400,"failed").add("message", "用户未找到");
        }else {
            return JsonBody.success(200,"success").add("user", user);
        }
    }

    @RequestMapping("/anonymous/registerUser")
    public JsonBody registerUser(@RequestBody User user) {
        user.setPermission(2);
        int result = userService.insertUser(user);
        if (result == 1) {
            return JsonBody.success(200,"success");
        }
        return JsonBody.failed(400,"failed");
    }

    //通过userId查找自身
    @RequestMapping("/anonymous/findUserByUserId")
    public JsonBody findUserByUserId(int userId) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            return JsonBody.failed(400,"failed").add("message", "用户未找到");
        }
        return JsonBody.success(200,"success").add("user", user);
    }

    //修改自身
    @RequestMapping("/user/modifyUser")
    public JsonBody modifyUser(@RequestBody User user) {
        int result = userService.modifyUser(user);
        if (result == 1) {
            return JsonBody.success(200,"success");
        }
        return JsonBody.failed(400,"failed").add("message", "修改失败");
    }

    //申请报修，保修单状态默认为未维修
    @RequestMapping("/user/applyRepair")
    public JsonBody applyRepair(@RequestBody RepairRequest repairRequest) {
        Repair repair = new Repair();
        repair.setUserId(repairRequest.getUserId());
        repair.setRepairmanId(repairRequest.getRepairmanId());
        repair.setDescription(repairRequest.getDescription());
        repair.setDeviceInfo(repair.getDeviceInfo());
        repair.setStatus("未修理");
        //上传图片
        boolean uploadImage = userService.uploadImage(repairRequest.getFile());

        repair.setStartTime(LocalDateTime.now());
        int result = userService.applyRepair(repair);
        if (result == 1) {
            return JsonBody.success(200,"success");
        } else if (!uploadImage) {
            return JsonBody.failed(400,"failed").add("message","图片上传错误");
        }
        return JsonBody.failed(400,"failed").add("message","申请失败");
    }

    //追踪自己的报修单状态
    //调用这个前端需要再调用getImage获取图片
    @RequestMapping("/user/trackRepair")
    public JsonBody trackRepair(int userId) {
        List<Repair> repairs = userService.trackRepair(userId);
        if (repairs==null||repairs.isEmpty()) {
            return JsonBody.success(200,"zero").add("repairs", repairs);
        }
        return JsonBody.success(200,"success").add("repairs", repairs);
    }

    //获取所有的报修
    //调用这个前端需要再调用getImage获取图片
    @RequestMapping("/anonymous/getRepairs")
    public JsonBody getRepairs() {
        List<Repair> repairs = userService.getRepairs();
        if (repairs==null||repairs.isEmpty()) {
            return JsonBody.success(200,"zero").add("repairs", repairs);
        }
        return JsonBody.success(200,"success").add("repairs", repairs);
    }

    //反馈
    @RequestMapping("/user/feedBack")
    public JsonBody feedBack(int repairId,LocalDateTime endTime, String feedback){
        int result = userService.feedBack(repairId, endTime, feedback);
        if (result == 1) {
            return JsonBody.success(200,"success");
        }
        return JsonBody.failed(400,"failed").add("message","反馈失败");
    }

    //获取图片
    @RequestMapping("/user/getImage")
    public void getImage(String fileName, HttpServletResponse response) throws Exception {
        ServletOutputStream stream = response.getOutputStream();
        userService.getImage(stream,fileName);
    }
}
