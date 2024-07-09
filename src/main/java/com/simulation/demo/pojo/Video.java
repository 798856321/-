package com.simulation.demo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Video {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//主键ID
    private String name;//视频名
    private String md5;//文件的md5
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间





}
