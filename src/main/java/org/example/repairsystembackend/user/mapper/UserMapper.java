package org.example.repairsystembackend.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.user.entity.User;

@Mapper
public interface UserMapper {

    //获取自身信息
    User getUserById(int userId);

    Repair getRepairByOrderId(int orderId);

    Repair getRepairByUserId(int userId);

    void insertRepair(Repair repair);

    void updateRepair(int userId, int orderId,String feedback,Double rating);

}
