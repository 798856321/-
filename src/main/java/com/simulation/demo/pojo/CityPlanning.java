package com.simulation.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CityPlanning {

    private String cityName;
    private String starName;
    private String endName;
}
