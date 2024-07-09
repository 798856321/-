package com.simulation.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simulation.demo.pojo.Admin;
import com.simulation.demo.pojo.Result;
import com.simulation.demo.pojo.User;
import com.simulation.demo.service.AdminService;
import com.simulation.demo.utils.JwtUtil;
import com.simulation.demo.utils.ThreadLocalUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    @ApiOperation(value = "管理员注册")
    public Result register(@RequestBody Admin admin_vo ) {


        //1 查询是否已经存在
        Admin admin = adminService.queryByAdminname(admin_vo.getUsername());
        if (admin != null) {
            log.info("用户已存在,注册失败{}", admin);
            return Result.error("用户已存在");

        }
        //2 新增
        else {
            adminService.add(admin_vo.getUsername(),admin_vo.getPassword());
        }
        return Result.success("注册管理员成功");
    }

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public Result login(@RequestBody Admin admin_vo ) {
        String username = admin_vo.getUsername();
        String password = admin_vo.getPassword();
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername, username);
        Admin admin = adminService.getOne(wrapper);
        if (admin == null)
            return Result.error("管理员不存在");
        if (password.equals(admin.getPassword())) {
            Map<String, Object> clamis = new HashMap<>();
            clamis.put("id", admin.getId());
            clamis.put("username", admin.getUsername());
            String token = JwtUtil.getToken(clamis);

            return Result.success(token);
        } else return Result.error("密码错误");
    }

    @ApiOperation(value = "获取当前管理员信息")
    @GetMapping("/adminInfo")
    public Result<Admin> adminInfo() {
        //从ThreadLocal中获取数据
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        //根据用户名查询管理员
        Admin admin = adminService.queryByAdminname(username);
        return Result.success(admin);

    }

    @ApiOperation(value = "更新管理员信息")
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        LambdaQueryWrapper<Admin> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Admin::getId,admin.getId());
        Admin admin1 = adminService.getOne(wrapper1);
        if(admin1 == null)
            return Result.error("该管理员不存在");
        admin.setUpdateTime(LocalDateTime.now());
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getId, admin.getId());
        adminService.update(admin, wrapper);

        return Result.success();
    }

    @ApiOperation(value = "删除管理员")
    @PutMapping("/delete")
    public Result delete(Integer id) {
        LambdaQueryWrapper<Admin> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Admin::getId,id);
        Admin admin1 = adminService.getOne(wrapper1);
        if(admin1 == null)
            return Result.error("该管理员不存在");
        adminService.removeById(id);
        return Result.success("删除管理员成功");
    }
}
