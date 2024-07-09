package com.simulation.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simulation.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username}")
    User queryByUsername(String username);

    @Insert("insert into user (username, password, create_time, update_time) " +
            "values (#{username},#{password},now(),now())")
    void add(String username,String password);

    @Select("select * from user ")
    List<User> queryAll();
}
