package com.simulation.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simulation.demo.pojo.Algorithm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AlgorithmMapper extends BaseMapper<Algorithm> {
    @Select("select * from algorithm where category_name = #{categoryName}")
    List<Algorithm> queryAll(String categoryName);
}
