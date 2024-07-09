package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.MapMapper;
import com.simulation.demo.pojo.map;
import com.simulation.demo.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl extends ServiceImpl<MapMapper, map> implements MapService {

    @Autowired
    private MapMapper mapMapper;

    @Override
    public map queryByName(String name) {
        return null;
    }

    @Override
    public void addMap(String name, String coordinate) {
        map map = new map();
        map.setName(name);
        map.setCoordinate(coordinate);
        mapMapper.insert(map);
    }


    @Override
    public List<map> queryAll() {
        List<map> maps = mapMapper.queryAllMap();
        return maps;
    }
}
