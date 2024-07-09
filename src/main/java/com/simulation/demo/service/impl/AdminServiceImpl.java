package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.AdminMapper;
import com.simulation.demo.pojo.Admin;
import com.simulation.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin queryByAdminname(String username) {
        Admin admin = adminMapper.queryByAdminUsername(username);
        return admin;
    }

    public void add(String username, String password) {
        adminMapper.add(username, password);
    }
}
