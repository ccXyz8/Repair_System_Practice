package org.example.repairsystembackend.user.service;

import org.example.repairsystembackend.user.entity.Repair;
import org.example.repairsystembackend.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public String modifyUser(User user) {
        return "";
    }

    @Override
    public String applyRepair(Repair repair) {
        return "";
    }

    @Override
    public Repair trackRepair(int userId) {
        return null;
    }

    @Override
    public String feedBack(int userId, int orderId, String feedback, Double rating) {
        return "";
    }
}
