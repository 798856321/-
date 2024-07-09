package com.simulation.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class NodeVo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//主键ID
    private String name;//节点名

}
