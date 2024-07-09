package com.simulation.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Node {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//主键ID
    private String mapName;//地图名
    private String name;//节点名
    private Integer isStart; //是起点还是终点 起点为1 终点为0

}
