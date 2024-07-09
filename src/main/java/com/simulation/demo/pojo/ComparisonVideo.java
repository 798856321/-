package com.simulation.demo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class ComparisonVideo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//主键ID
    private String name;//视频名
    private String comparison;//对照视频名








}
