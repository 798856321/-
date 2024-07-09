package com.simulation.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simulation.demo.pojo.Algorithm;
import com.simulation.demo.pojo.Category;
import com.simulation.demo.pojo.Result;
import com.simulation.demo.pojo.map;
import com.simulation.demo.service.AlgorithmService;
import com.simulation.demo.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/algorithm")
public class AlgorithmController {
    @Autowired
    private AlgorithmService algorithmService;
    @Autowired
    private CategoryService categoryService;
    @PutMapping("/addAlgorithm")
    @ApiOperation(value = "算法添加")
    public Result register(String categoryName,String name) {
        //1 查询类别是否已经存在
        LambdaQueryWrapper<Category> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Category::getName,categoryName);
        Category category = categoryService.getOne(wrapper1);
        if (category == null) {
            return Result.error("算法类别不存在,请先上传");
        }
        //2 查询算法是否已经存在
        LambdaQueryWrapper<Algorithm> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Algorithm::getName,name);
        Algorithm algorithm = algorithmService.getOne(wrapper);
        if (algorithm!= null) {
            return Result.error("算法已存在,请勿重复上传");
        }

        //2 新增
        else
        {

            algorithmService.addAlgorithm(categoryName,name);
        }
        return Result.success("添加算法成功");
    }

    @ApiOperation(value = "删除算法")
    @DeleteMapping("/delete")
    public Result delete(Integer id){
        //1 查询是否已经存在
        LambdaQueryWrapper<Algorithm> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Algorithm::getId,id);
        Algorithm algorithm = algorithmService.getOne(wrapper);
        if (algorithm == null) {
            return Result.error("将要删除的算法不存在");
        }
        algorithmService.removeById(id);
        return Result.success("删除算法成功");
    }

    @ApiOperation(value = "获取算法列表")
    @GetMapping("/allalgorithms")
    public Result<List<Algorithm>> getAllalgorithms(String categoryName){
        //1 查询类别是否已经存在
        LambdaQueryWrapper<Category> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Category::getName,categoryName);
        Category category = categoryService.getOne(wrapper1);
        if (category == null) {
            return Result.error("算法类别不存在,请先上传");
        }
        List<Algorithm> algorithms =algorithmService.queryAll(categoryName);
        return Result.success(algorithms);
    }

    @ApiOperation(value = "添加视频")
    @PostMapping("/addVideo")
    public Result addVideo(String name,String video,String controlVideo){
        //判断是否存在该算法
        LambdaQueryWrapper<Algorithm> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Algorithm::getName,name);
        Algorithm algorithm = algorithmService.getOne(wrapper);
        if (algorithm == null) {
            return Result.error("算法不存在,请先添加算法");
        }
        Integer id = algorithm.getId();
//        String name1 = algorithm.getName();
//        String categoryName = algorithm.getCategoryName();
//        Algorithm algorithm1 = new Algorithm();
        algorithm.setVideo(video);
        algorithm.setControlVideo(controlVideo);
        LambdaQueryWrapper<Algorithm> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Algorithm::getId,id);
        algorithmService.update(algorithm,lambdaQueryWrapper);
        return Result.success("添加视频成功");
    }
}


