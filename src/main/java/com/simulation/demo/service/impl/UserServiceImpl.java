package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.UserMapper;
import com.simulation.demo.pojo.User;
import com.simulation.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryByUsername(String username) {
        User user = userMapper.queryByUsername(username);
        return user;
    }

    public void add(String username, String password) {
        userMapper.add(username, password);
    }

    @Override
    public List<User> queryAll() {
      List<User>  users =  userMapper.queryAll();
        return users;
    }
}
