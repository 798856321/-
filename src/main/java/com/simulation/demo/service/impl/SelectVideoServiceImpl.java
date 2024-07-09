package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.AlgorithmMapper;
import com.simulation.demo.mapper.SelectVideoMapper;
import com.simulation.demo.pojo.SelectVideo;
import com.simulation.demo.service.SelectVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectVideoServiceImpl extends ServiceImpl<SelectVideoMapper, SelectVideo> implements SelectVideoService {
    @Autowired
    private SelectVideoMapper selectVideoMapper;
    @Override
    public void addVideoByCondition(String name, String condition1, String condition2) {
        SelectVideo selectVideo = new SelectVideo();

        selectVideo.setName(name);
        selectVideo.setCondition1(condition1);
        selectVideo.setCondition2(condition2);

        selectVideoMapper.insert(selectVideo);
    }
}
