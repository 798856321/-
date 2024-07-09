package com.simulation.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simulation.demo.pojo.Category;
import com.simulation.demo.pojo.map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select * from category ")
    List<Category> queryAllCategories();
}
