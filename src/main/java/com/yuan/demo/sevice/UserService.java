package com.yuan.demo.sevice;

import com.yuan.demo.mapper.UserMapper;
import com.yuan.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getByUserName(String username){
        return userMapper.getByUserName(username);
    }
}
