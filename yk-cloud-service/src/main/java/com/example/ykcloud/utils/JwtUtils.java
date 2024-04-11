package com.example.ykcloud.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

//    @Bean
//    public JwtUtils getJwtUtils(){
//        return new JwtUtils();
//    }
//
//    //配置文件配置的
//    @Value("${jwt.ttl}")
//    private long ttl;  //指定token有效时间 (分钟)
//
//    //密钥
//    @Value("${jwt.secret}")
//    private String secret;

//    //生成Token
//    public String buildToken(String userId, String userName){
//        long currentTimeMillis = System.currentTimeMillis(); //当前系统时间
//
//        long expirationTimeMillis = currentTimeMillis + 1000*60*ttl; //过期时间
//
//        String token = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256, secret)//指定加密算法和密钥
//                .setId(userId) //添加载荷部分键值对 => 放置登录用户id
//                .setSubject(userName)  //添加载荷部分键值对 => 放置登录用户名
//                .setIssuedAt(new Date()) //添加载荷部分键值对 => 放置签发token时间
//                .setExpiration(new Date(expirationTimeMillis))//指定token的过期时间
//                .compact();//生成密文token
//        return token;
//    }
//    //解析Token
//    public Claims parseToken(String token){
//        Claims claims = Jwts.parser() //返回token解析器
//                .setSigningKey(secret) //指定解析使用的密钥 => 需要跟加密时使用相同的密钥
//                .parseClaimsJws(token) // 指定要解密的Token
//                .getBody();//获得载荷部分
//        return claims;
//    }


}
