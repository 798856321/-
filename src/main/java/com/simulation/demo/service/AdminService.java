package com.simulation.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simulation.demo.pojo.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService extends IService<Admin> {
    Admin queryByAdminname(String username);

    void add(String username,String password);
}
