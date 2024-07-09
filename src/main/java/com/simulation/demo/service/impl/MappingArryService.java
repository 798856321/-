package com.simulation.demo.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MappingArryService {

    // 定义一个静态的映射表
    private static final Map<String, Double[]> STRING_TO_Double_MAP = new HashMap<>();

    static {
        STRING_TO_Double_MAP.put("西北大学(太白)",new Double[]{108.927083,34.248333});
        STRING_TO_Double_MAP.put("西安钟楼",new Double[]{108.94703,34.25943});
        STRING_TO_Double_MAP.put("西北大学(长安)",new Double[]{108.874582,34.144994});
        STRING_TO_Double_MAP.put("长安公园",new Double[]{108.912884,34.142549});
        // 你可以在这里添加更多的映射
    }

    public Double[] convertStringToDouble(String input) {
        return STRING_TO_Double_MAP.getOrDefault(input,new Double[]{-1.1}); // 返回 -1.1 表示未找到映射
    }
}
