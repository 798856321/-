package com.simulation.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simulation.demo.pojo.Video;
import com.simulation.demo.pojo.map;
import org.springframework.stereotype.Service;

@Service
public interface VideoService extends IService<Video> {


    void addVideo(String filename, String md5Result);
}
