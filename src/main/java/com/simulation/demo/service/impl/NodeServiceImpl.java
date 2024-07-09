package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.MapMapper;
import com.simulation.demo.mapper.NodeMapper;
import com.simulation.demo.pojo.Node;
import com.simulation.demo.pojo.map;
import com.simulation.demo.service.MapService;
import com.simulation.demo.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements NodeService {


}
