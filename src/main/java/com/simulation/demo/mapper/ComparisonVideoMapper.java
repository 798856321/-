package com.simulation.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simulation.demo.pojo.Algorithm;
import com.simulation.demo.pojo.ComparisonVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ComparisonVideoMapper extends BaseMapper<ComparisonVideo> {

}
