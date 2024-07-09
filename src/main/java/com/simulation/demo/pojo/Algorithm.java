package com.simulation.demo.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Algorithm {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//主键ID
    private String name;//算法名
    private String categoryName;//类别名

    private String video;//视频
    private String controlVideo;//原始对照视频


}
