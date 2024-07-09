package com.simulation.demo.Exception;


import com.simulation.demo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.info("逮到异常啦~{}",e.getMessage());
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())?
                e.getMessage():"操作失败");
    }
}
