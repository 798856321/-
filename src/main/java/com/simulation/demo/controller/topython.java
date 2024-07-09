package com.simulation.demo.controller;

import com.mysql.cj.log.Log;
import com.simulation.demo.pojo.CityPlanning;
import com.simulation.demo.pojo.CityPlanningtoPoint;
import com.simulation.demo.pojo.Result;
import com.simulation.demo.service.impl.MappingArryService;
import com.simulation.demo.service.impl.MappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
    public class topython {
        @Autowired
        private MappingService mappingService;
        @Autowired
        private MappingArryService  mappingArryService;


        @PostMapping("/call-python")
        public Result callPythonApi(@RequestBody CityPlanning cityPlanning) {


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

    @PostMapping("/call-python1")
    public Result callPythonApi1(String string) {
        String pythonApiUrl = "http://localhost:5001/process";
        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建请求实体，包含头和请求体
        HttpEntity<String> entity = new HttpEntity<>(string, headers);

        // 发送 POST 请求到 Python API 并获取响应
        ResponseEntity<String> response = restTemplate.exchange(pythonApiUrl, HttpMethod.POST, entity, String.class);

        // 返回 Python API 的响应
        return Result.success(response.getBody());

    }
    }


