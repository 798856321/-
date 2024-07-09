package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.AlgorithmMapper;
import com.simulation.demo.mapper.CategoryMapper;
import com.simulation.demo.pojo.Algorithm;
import com.simulation.demo.pojo.Category;
import com.simulation.demo.service.AlgorithmService;
import com.simulation.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements AlgorithmService {
@Autowired
private AlgorithmMapper algorithmMapper;
    @Override
    public List<Algorithm> queryAll(String categoryName) {
        List<Algorithm> algorithms= algorithmMapper.queryAll(categoryName);
        return algorithms;
    }

    @Override
    public void addAlgorithm(String categoryName, String name) {
        Algorithm algorithm = new Algorithm();
        algorithm.setName(name);
        algorithm.setCategoryName(categoryName);
//        algorithm.setVideo("无");
//        algorithm.setControlVideo("无");
        algorithmMapper.insert(algorithm);
    }
}
