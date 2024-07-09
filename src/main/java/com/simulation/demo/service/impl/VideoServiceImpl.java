package com.simulation.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simulation.demo.mapper.MapMapper;
import com.simulation.demo.mapper.VideoMapper;
import com.simulation.demo.pojo.Video;
import com.simulation.demo.pojo.map;
import com.simulation.demo.service.MapService;
import com.simulation.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VideoMapper videoMapper;


    @Override
    public void addVideo(String filename, String md5Result) {
        Video video = new Video();
        video.setMd5(md5Result);
        video.setName(filename);
        video.setCreateTime((LocalDateTime.now()));
        video.setUpdateTime(LocalDateTime.now());
        videoMapper.insert(video);
    }
}
