package com.simulation.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simulation.demo.pojo.ComparisonVideo;
import com.simulation.demo.pojo.SelectVideo;
import org.springframework.stereotype.Service;

@Service
public interface ComparisonVideoService extends IService<ComparisonVideo> {


    void addComparisonVideo(String name, String comparison);
}
