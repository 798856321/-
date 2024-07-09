package com.simulation.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simulation.demo.pojo.Result;
import com.simulation.demo.pojo.User;
import com.simulation.demo.pojo.map;
import com.simulation.demo.service.UserService;
import com.simulation.demo.utils.JwtUtil;
import com.simulation.demo.utils.ThreadLocalUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Result register( @RequestBody User user_vo ) {



        //1 查询是否已经存在
        User user = userService.queryByUsername(user_vo.getUsername());
        log.info("user:{}",user);
        if (user != null) {
            log.info("用户已存在,注册失败{}",user);
            return Result.error("用户已存在");

        }
        //2 新增
        else
        {
            userService.add(user_vo.getUsername(),user_vo.getPassword());
        }
        return Result.success();
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result login( @RequestBody User user_vo){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user_vo.getUsername());
        User user = userService.getOne(wrapper);
        if(user == null)
            return Result.error("用户名不存在");
        if(user_vo.getPassword().equals(user.getPassword()))
        {
            Map<String, Object> clamis = new HashMap<>();
            clamis.put("id",user.getId());
            clamis.put("username",user.getUsername());
            String token = JwtUtil.getToken(clamis);

            return Result.success(token);
        }
        else return Result.error("密码错误");
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        //从ThreadLocal中获取数据
        Map<String,Object> claims = ThreadLocalUtil.get();
        String username = (String)claims.get("username");
        //根据用户名查询用户
        User user = userService.queryByUsername(username);
        log.info("User{}",user);
        return Result.success(user);

    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        LambdaQueryWrapper<User> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(User::getId,user.getId());
        User user1 = userService.getOne(wrapper1);
        if(user1 == null)
            return Result.error("用户不存在");
        user.setUpdateTime(LocalDateTime.now());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,user.getId());
        userService.update(user, wrapper);

        return Result.success("更新用户信息成功");
    }

    @ApiOperation(value = "删除用户")
    @PutMapping("/delete")
    public Result delete(Integer id){
        LambdaQueryWrapper<User> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(User::getId,id);
        User user1 = userService.getOne(wrapper1);
        if(user1 == null)
            return Result.error("用户不存在");
        userService.removeById(id);
        return Result.success("删除用户成功");
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/allusers")
    public Result<List<User>> getAllUsers(){
        List<User> users =userService.queryAll();
        return Result.success(users);
    }

}


