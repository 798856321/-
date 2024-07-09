package com.simulation.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simulation.demo.pojo.*;
import com.simulation.demo.service.MapService;
import com.simulation.demo.service.NodeService;
import com.simulation.demo.service.UserService;
import com.simulation.demo.utils.JwtUtil;
import com.simulation.demo.utils.ThreadLocalUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @PostMapping("/addMap")
    @ApiOperation(value = "地图添加")
    public Result register( String name,String coordinate) {
        //1 查询是否已经存在
        LambdaQueryWrapper<map> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(map::getName,name);
        map map = mapService.getOne(wrapper);
        if (map != null) {
            return Result.error("地图已存在,请勿重复上传");
        }

        //2 新增
        else
        {
            mapService.addMap(name,coordinate);
        }
        return Result.success("添加地图成功");
    }



    @ApiOperation(value = "删除地图")
    @GetMapping("/delete")
    public Result delete(Integer id){
        //1 查询是否已经存在
        LambdaQueryWrapper<map> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(map::getId,id);
        map map = mapService.getOne(wrapper);
        if (map == null) {
            return Result.error("将要删除的地图不存在");
        }
        mapService.removeById(id);
        return Result.success("删除地图成功");
    }

    @ApiOperation(value = "获取地图列表")
    @GetMapping("/allmap")
    public Result<List<map>> getAllMap(){
       List<map> maps =mapService.queryAll();
        return Result.success(maps);
    }


    @Autowired
    private NodeService nodeService;
    @ApiOperation(value = "获取起点")
    @GetMapping("/getStarNode")
    public Result<List<NodeVo>> getStarNode(String mapName){
        LambdaQueryWrapper<Node> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Node::getMapName,mapName).eq(Node::getIsStart,1);
        List<Node> nodes = nodeService.list(wrapper);
        int size = nodes.size();
        List<NodeVo> nodeVos = new ArrayList<>();
        for(int i = 0; i < size; i++){
            NodeVo nodeVo = new NodeVo();
            nodeVo.setId(nodes.get(i).getId());
            nodeVo.setName(nodes.get(i).getName());
            nodeVos.add(nodeVo);
        }
        return Result.success(nodeVos);
    }


    @ApiOperation(value = "获取终点")
    @GetMapping("/getEndNode")
    public Result<List<NodeVo>> getEndNode(String mapName){
        LambdaQueryWrapper<Node> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Node::getMapName,mapName).eq(Node::getIsStart,0);
        List<Node> nodes = nodeService.list(wrapper);
        int size = nodes.size();
        List<NodeVo> nodeVos = new ArrayList<>();
        for(int i = 0; i < size; i++){
            NodeVo nodeVo = new NodeVo();
            nodeVo.setId(nodes.get(i).getId());
            nodeVo.setName(nodes.get(i).getName());
            nodeVos.add(nodeVo);
        }
        return Result.success(nodeVos);
    }






}


