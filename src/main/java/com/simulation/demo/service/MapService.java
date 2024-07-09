package com.simulation.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simulation.demo.pojo.map;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService extends IService<map> {
    map queryByName(String name);



    void addMap(String name, String coordinate);

    List<map> queryAll();

}
