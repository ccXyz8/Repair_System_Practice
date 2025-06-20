package org.example.repairsystembackend.user.controller;

import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.user.entity.User;
import org.example.repairsystembackend.user.entity.request.RepairRequest;
import org.example.repairsystembackend.user.service.UserService;
import org.example.repairsystembackend.utils.view.JsonBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.example.repairsystembackend.utils.url.UrlConstants.URL_V1_VERSION_PREFIX;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //查找自身
    @RequestMapping("/anonymous/findUserByUsername")
    public JsonBody findUserByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return JsonBody.failed(400,"failed");
        }else {
            return JsonBody.success(200,"success").add("user", user);
        }
    }

    //查找自身
    @RequestMapping("/anonymous/findUserByUserId")
    public JsonBody findUserByUserId(int userId) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            return JsonBody.failed(400,"failed");
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
        return JsonBody.failed(400,"failed");
    }

    //申请报修
    @RequestMapping("/user/applyRepair")
    public JsonBody applyRepair(@RequestBody RepairRequest repairRequest) throws IOException {
        Repair repair = new Repair();
        repair.setUserId(repairRequest.getUserId());
        repair.setRepairmanId(repairRequest.getRepairmanId());
        repair.setDescription(repairRequest.getDescription());
        repair.setDeviceInfo(repair.getDeviceInfo());

        //上传图片
        boolean uploadImage = userService.uploadImage(repairRequest.getFile());

        repair.setStartTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        int result = userService.applyRepair(repair);
        if (result == 1) {
            return JsonBody.success(200,"success");
        } else if (!uploadImage) {
            return JsonBody.failed(400,"failed").add("description","图片上传错误");
        }
        return JsonBody.failed(400,"failed");
    }

    //
    @RequestMapping("/user/trackRepair")
    public JsonBody trackRepair(int userId,HttpServletResponse response) throws Exception {
        List<Repair> repairs = userService.trackRepair(userId);
        if (repairs==null||repairs.isEmpty()) {
            return JsonBody.failed(400,"failed");
        }
        for (Repair repair : repairs) {
            getImage(repair.getPicture(),response);
        }
        return JsonBody.success(200,"success").add("repairs", repairs);
    }

    //获取所有的报修
    @RequestMapping("/anonymous/getRepairs")
    public JsonBody getRepairs(HttpServletResponse response) throws Exception {
        List<Repair> repairs = userService.getRepairs();
        if (repairs==null||repairs.isEmpty()) {
            return JsonBody.failed(400,"failed");
        }
        for (Repair repair : repairs) {
            getImage(repair.getPicture(),response);
        }
        return JsonBody.success(200,"success").add("repairs", repairs);
    }

    //反馈
    @RequestMapping("/user/feedBack")
    public JsonBody feedBack(int orderId, String endTime, String feedback){
        int result = userService.feedBack(orderId, endTime, feedback);
        if (result == 1) {
            return JsonBody.success(200,"success");
        }
        return JsonBody.failed(400,"failed");
    }

    public void getImage(String fileName, HttpServletResponse response) throws Exception {
        ServletOutputStream stream = response.getOutputStream();
        userService.getImage(stream,fileName);
    }
}
