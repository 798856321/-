package com.simulation.demo.interceptors;


import com.simulation.demo.utils.JwtUtil;
import com.simulation.demo.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component //注册需要拦截器对象 所以放入ioc中
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        log.info("当前请求url:{}",path);
        //令牌验证
         //获取JWT
        String token = request.getHeader("Authorization");

        //验证token  如果正常执行就验证成功  如果抛异常就证明失败
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            log.info("拦截通过~");
            return true; //放行
        } catch (Exception e) {
            //根据接口说明验证失败 设置http响应码为401
            e.printStackTrace();
            response.setStatus(401);
            log.info("拦截成功~");
            return false;//不放行
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        //清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
