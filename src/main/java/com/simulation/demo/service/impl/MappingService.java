package com.simulation.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class MappingService {

    // 定义一个静态的映射表
    private static final Map<String, Integer> STRING_TO_NUMBER_MAP = new HashMap<>();

    static {
        STRING_TO_NUMBER_MAP.put("武汉", 0);
        STRING_TO_NUMBER_MAP.put("西安", 1);
        STRING_TO_NUMBER_MAP.put("台湾", 2);
        STRING_TO_NUMBER_MAP.put("旧金山", 3);
        STRING_TO_NUMBER_MAP.put("波尔图", 4);
        // 你可以在这里添加更多的映射
    }

    public Integer convertStringToNumber(String input) {
        return STRING_TO_NUMBER_MAP.getOrDefault(input, -1); // 返回 -1 表示未找到映射
    }
}
