package com.simulation.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simulation.demo.pojo.Algorithm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlgorithmService extends IService<Algorithm> {

    List<Algorithm> queryAll(String categoryName);

    void addAlgorithm(String categoryName, String name);
}
