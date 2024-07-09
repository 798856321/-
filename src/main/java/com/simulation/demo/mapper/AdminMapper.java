package com.simulation.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simulation.demo.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from admin where username = #{username}")
    Admin queryByAdminUsername(String username);

    @Insert("insert into admin (username, password, create_time, update_time) " +
            "values (#{username},#{password},now(),now())")
    void add(String username,String password);
}
