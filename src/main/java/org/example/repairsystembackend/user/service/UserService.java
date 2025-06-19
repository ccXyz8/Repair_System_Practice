package org.example.repairsystembackend.user.service;


import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.user.entity.User;

public interface UserService {

    User findByUsername(String username);

    String modifyUser(User user);

    String applyRepair(Repair repair);

    Repair trackRepair(int userId);

    String feedBack(int userId, int orderId,String feedback,Double rating);

}
