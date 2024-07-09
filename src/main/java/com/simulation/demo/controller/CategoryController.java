package com.simulation.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simulation.demo.pojo.Category;
import com.simulation.demo.pojo.Result;
import com.simulation.demo.pojo.map;
import com.simulation.demo.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PutMapping("/addCategory")
    @ApiOperation(value = "算法类别添加")
    public Result register(String name) {
        //1 查询是否已经存在
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName,name);
        Category category = categoryService.getOne(wrapper);
        if (category!= null) {
            return Result.error("算法类别已存在,请勿重复上传");
        }

        //2 新增
        else
        {
            categoryService.addCategory(name);
        }
        return Result.success("添加类别成功");
    }

    @ApiOperation(value = "删除类别")
    @DeleteMapping("/delete")
    public Result delete(Integer id){
        //1 查询是否已经存在
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getId,id);
        Category category = categoryService.getOne(wrapper);
        if (category == null) {
            return Result.error("将要删除的类别不存在");
        }
        categoryService.removeById(id);
        return Result.success("删除类别成功");
    }

    @ApiOperation(value = "获取类别列表")
    @GetMapping("/allcategories")
    public Result<List<Category>> getAllCategories(){
        List<Category> categories =categoryService.queryAll();
        return Result.success(categories);
    }

}


