package com.example.ykcloud.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ykcloud.Service.UserService;
import com.example.ykcloud.entity.User;
import com.example.ykcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(String userName) {
        User user = userMapper.getUserByName(userName);
        return user;
    }


    @Override
    public String getName(String userId, String passWord) {
        String name =userMapper.getUserName(userId,passWord);
        return name;
    }


}
