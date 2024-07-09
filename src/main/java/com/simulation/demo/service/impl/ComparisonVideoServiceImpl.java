package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.ComparisonVideoMapper;
import com.simulation.demo.pojo.ComparisonVideo;
import com.simulation.demo.service.ComparisonVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComparisonVideoServiceImpl extends ServiceImpl<ComparisonVideoMapper, ComparisonVideo> implements ComparisonVideoService {
    @Autowired ComparisonVideoMapper comparisonVideoMapper;
    @Override
    public void addComparisonVideo(String name, String comparison) {
        ComparisonVideo comparisonVideo = new ComparisonVideo();
        comparisonVideo.setName(name);
        comparisonVideo.setComparison(comparison);
        comparisonVideoMapper.insert(comparisonVideo);
    }
}
