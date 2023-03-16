package com.example.springsecuritydemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 君墨笑
 * @date 2023/3/14
 */
public class JWTUtils {

    private static final String SECRET = "snjsnjsjs";


    /**
     * 创建JWT Token
     */
    public static String createToken(String userId, String userName) {
        //设置Token过期时间为2小时
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,60);
        String token = JWT.create().withHeader(new HashMap<>())
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }


    /**
     * 解析JWT
     *
     */
    public static Map<String,String> analyzeToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String userId = decodedJWT.getClaim("userId").asString();
            String userName = decodedJWT.getClaim("userName").asString();
            Map<String,String> map = new HashMap<>();
            map.put("userId",userId);
            map.put("userName",userName);
            return map;
        } catch (TokenExpiredException e) {
            System.out.println("Token已经过期");
        } catch (Exception x) {
            System.out.println("Token解析失败");
        }
        return null;
    }
}

