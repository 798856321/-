package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.CategoryMapper;
import com.simulation.demo.pojo.Category;
import com.simulation.demo.pojo.map;
import com.simulation.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryMapper.insert(category);

    }

    @Override
    public List<Category> queryAll() {
        List<Category> categories = categoryMapper.queryAllCategories();
        return categories;
    }
}
