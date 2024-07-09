package com.simulation.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simulation.demo.pojo.CityPlanning;
import com.simulation.demo.pojo.CityPlanningtoPoint;
import com.simulation.demo.pojo.Result;
import com.simulation.demo.pojo.Video;
import com.simulation.demo.service.VideoService;
import com.simulation.demo.service.impl.MappingArryService;
import com.simulation.demo.service.impl.MappingService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private VideoService videoService;
    @PostMapping
    public Result upload(@RequestParam("file")MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return Result.error("上传失败");
        }

        /*
        对文件名进行UUID转换
        */
        String originalFilename = file.getOriginalFilename();

        //将原始文件名进行md5
        String md5Result = org.springframework.util.DigestUtils.md5DigestAsHex(originalFilename.getBytes());
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //根据md5判断是否已经存在
        LambdaQueryWrapper<Video> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Video::getMd5,md5Result);
        Video video = videoService.getOne(wrapper);
        if (video != null) {
            return Result.error("视频已存在,请勿重复上传");
        }

        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        // 获取项目下resources/static/img路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

//        String path = "D:\\upload\\" + originalFilename;
//        file.transferTo(new File(path));
        // 保存目录位置根据项目需求可随意更改
        String url = applicationHome.getDir().getParentFile()
                .getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\video\\";

        String path = url+originalFilename;

        file.transferTo(new File(path));


        videoService.addVideo(originalFilename,md5Result);

        return Result.success(originalFilename);

    }



        @PostMapping("/Image")
        public Result uploadImage(@RequestParam("file")MultipartFile file,
                                  @RequestParam("AttackMethods")String AttackMethods) throws IOException {
            if (file.isEmpty()){
                return Result.error("上传失败");
            }

            log.info("file:{}",file.getOriginalFilename());
            log.info("attackMethods{}",AttackMethods);
            return Result.success("有障碍物");

        }

@Autowired
private MappingService mappingService;
    @Autowired
    private MappingArryService mappingArryService;
    @PostMapping("/coordinate")
    public Result coordinate(@RequestBody CityPlanning cityPlanning) {


        String pythonApiUrl = "http://localhost:5000/planning";
        log.info("CityName:{}",cityPlanning.getCityName());
        log.info("StarName:{}",cityPlanning.getStarName());
        log.info("endName:{}",cityPlanning.getEndName());
        Integer CityId = mappingService.convertStringToNumber(cityPlanning.getCityName());
        Double[] starPoint = mappingArryService.convertStringToDouble(cityPlanning.getStarName());
        Double[] endPoint = mappingArryService.convertStringToDouble(cityPlanning.getEndName());
        log.info("CityId:{}",CityId);
        log.info("starPoint:{}{}",starPoint[0],starPoint[1]);
        log.info("endPoint:{}{}",endPoint[0],endPoint[1]);
        CityPlanningtoPoint cityPlanningtoPoint = new CityPlanningtoPoint();
        cityPlanningtoPoint.setCityId(CityId);
        cityPlanningtoPoint.setStarPoint(starPoint);
        cityPlanningtoPoint.setEndPoint(endPoint);
        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建请求实体，包含头和请求体
        HttpEntity<CityPlanningtoPoint> entity = new HttpEntity<>(cityPlanningtoPoint, headers);

        // 发送 POST 请求到 Python API 并获取响应
        ResponseEntity<String> response = restTemplate.exchange(pythonApiUrl, HttpMethod.POST, entity, String.class);

        // 返回 Python API 的响应
        return Result.success(response.getBody().replace("\\", "").replace("\n", ""));
    }
}