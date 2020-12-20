package com.hua.shirospringboot.service;


import com.hua.shirospringboot.mapper.UserMapper;
import com.hua.shirospringboot.pojo.userInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper ;

    @Override
    public userInfo getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
