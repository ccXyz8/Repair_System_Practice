package org.example.repairsystembackend.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {

    //获取自身信息
    User getUserById(int userId);

    User getUserByUsername(String username);

    int updateUser(User user);

    Repair getRepairByOrderId(int orderId);

    List<Repair> getRepairByUserId(int userId);

    List<Repair> getRepairs();

    int insertRepair(Repair repair);

    int updateRepair(int orderId, String endTime, String feedback);

}
