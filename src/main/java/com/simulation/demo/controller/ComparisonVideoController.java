package com.simulation.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simulation.demo.pojo.ComparisonVideo;
import com.simulation.demo.pojo.Result;
import com.simulation.demo.pojo.SelectVideo;
import com.simulation.demo.service.ComparisonVideoService;
import com.simulation.demo.service.SelectVideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/comparisonVideo")
public class ComparisonVideoController {
    @Autowired
    private ComparisonVideoService comparisonVideoService;

    @PutMapping("/addComparisonVideo")
    @ApiOperation(value = "添加对照视频")
    public Result addComparisonVideo(String name,String comparison) {
        //1 查询视频是否已经存在
        LambdaQueryWrapper<ComparisonVideo> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(ComparisonVideo::getName,name);
        ComparisonVideo comparisonVideo = comparisonVideoService.getOne(wrapper1);
        if (comparisonVideo != null) {
            return Result.error("对照视频已存在,不要重复上传");
        }
        //2 新增
        else
        {
            comparisonVideoService.addComparisonVideo(name,comparison);
        }
        return Result.success("添加视频成功");
    }

    @ApiOperation(value = "删除对照视频")
    @DeleteMapping("/delete")
    public Result delete(Integer id){
        //1 查询是否已经存在
        LambdaQueryWrapper<ComparisonVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ComparisonVideo::getId,id);
        ComparisonVideo comparisonVideo = comparisonVideoService.getOne(wrapper);
        if (comparisonVideo == null) {
            return Result.error("将要删除的对照视频不存在");
        }
        comparisonVideoService.removeById(id);
        return Result.success("删除对照视频成功");
    }

    @ApiOperation(value = "按名称查找对应对照视频")
    @GetMapping("/getComparisonVideo")
    public Result getComparisonVideo( String name){
        LambdaQueryWrapper<ComparisonVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ComparisonVideo::getName,name);
        ComparisonVideo comparisonVideo = comparisonVideoService.getOne(wrapper);
        if (comparisonVideo == null) {
            return Result.error("查找失败");
        }
        return Result.success(comparisonVideo);
    }

}




