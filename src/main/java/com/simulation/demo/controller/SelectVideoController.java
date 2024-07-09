package com.simulation.demo.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simulation.demo.pojo.*;
import com.simulation.demo.service.SelectVideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/selectVideo")
public class SelectVideoController {
    @Autowired
    private SelectVideoService selectVideoService;

    @PutMapping("/addVideoByCondition")
    @ApiOperation(value = "添加视频条件")
    public Result addVideoByCondition(String name,String condition1,String condition2) {
        //1 查询视频是否已经存在
        LambdaQueryWrapper<SelectVideo> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(SelectVideo::getName,name);
        SelectVideo selectVideo = selectVideoService.getOne(wrapper1);
        if (selectVideo != null) {
            return Result.error("视频已存在,不要重复上传");
        }
        //2 新增
        else
        {
            selectVideoService.addVideoByCondition(name,condition1,condition2);
        }
        return Result.success("添加视频成功");
    }

    @ApiOperation(value = "删除带条件的视频")
    @DeleteMapping("/delete")
    public Result delete(Integer id){
        //1 查询是否已经存在
        LambdaQueryWrapper<SelectVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SelectVideo::getId,id);
        SelectVideo selectVideo = selectVideoService.getOne(wrapper);
        if (selectVideo == null) {
            return Result.error("将要删除的视频不存在");
        }
        selectVideoService.removeById(id);
        return Result.success("删除视频成功");
    }

    @ApiOperation(value = "按单个条件查找")
    @GetMapping("/getVideoByCondition")
    public Result getVideoByCondition( String condition){
        LambdaQueryWrapper<SelectVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SelectVideo::getCondition1,condition);
        SelectVideo selectVideo = selectVideoService.getOne(wrapper);
        if (selectVideo == null) {
           return Result.error("查找失败");
        }
        return Result.success(selectVideo);
    }

    @ApiOperation(value = "按两个个条件查找")
    @GetMapping("/getVideoByConditions")
    public Result getVideoByConditions(String condition1,String condition2){
        LambdaQueryWrapper<SelectVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SelectVideo::getCondition1,condition1).eq(SelectVideo::getCondition2,condition2);
        SelectVideo selectVideo = selectVideoService.getOne(wrapper);
        if (selectVideo == null) {
            return Result.error("查找失败");
        }
        return Result.success(selectVideo);
    }
}




