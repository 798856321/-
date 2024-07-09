package com.simulation.demo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//主键ID
    private String username;//用户名
    private String password;//密码
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
