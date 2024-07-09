package com.simulation.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simulation.demo.pojo.map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MapMapper extends BaseMapper<map> {

    @Select("select * from map ")
    List<map> queryAllMap();
}
