package com.simulation.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simulation.demo.pojo.SelectVideo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SelectVideoService extends IService<SelectVideo> {


    void addVideoByCondition(String name, String condition1, String condition2);
}
