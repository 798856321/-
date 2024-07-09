package com.simulation.demo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class map {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//主键ID
    private String name;//地图名
    private String coordinate;//坐标

}
