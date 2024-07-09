package com.simulation.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

    private static final String KEY = "Yuyu";

    //接收载荷数据,生成token并返回
    public static String getToken(Map<String,Object> claims){
        String sign = JWT.create()
                .withClaim("claims", claims)//添加载荷数据
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//设置失效时间 12h
                .sign(Algorithm.HMAC256(KEY));
        return sign;
    }

    //接收token,验证token,并返回载荷数据
    public static Map<String,Object> parseToken(String token){
        //按照生成时的算法以及密钥生成验证器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(KEY)).build();
        //验证传入的token,生成解码后的数据
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        //获取其中的claims载荷数据
        Map<String, Object> claims = decodedJWT.getClaim("claims").asMap();
        return claims;

    }
}
