package com.simulation.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simulation.demo.mapper.UserMapper;
import com.simulation.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends IService<User> {
    User queryByUsername(String username);

    void add(String username,String password);

    List<User> queryAll();
}
