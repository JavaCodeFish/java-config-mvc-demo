package com.yuan.demo.mapper;

import com.yuan.demo.model.User;

public interface UserMapper {
    public User getByUserName(String username);
}
