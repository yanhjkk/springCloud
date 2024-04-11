package com.example.ykcloud.config;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.example.ykcloud.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
//    @Autowired
//    private JwtUtils jwtUtils;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("经过了拦截器");
//
//        // 判断是否为 OPTIONS 请求，如果是则返回 true 放行
//        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
//            return true;
//        }
//        //获得放置token的请求头
//        String token = request.getHeader("Authorization");
//        //校验头格式
//        if(StringUtils.isEmpty(token) || !token.startsWith("Bearer ")){
//            throw new RuntimeException("请先登录");
//        }
//        //截取token字符串
//        token = token.substring(7);
//        //解析token
//        try {
//            Claims claims = jwtUtils.parseToken(token);
//            request.setAttribute("claims",claims);
//        } catch (Exception e) {
//            throw new RuntimeException("请先登录");
//        }
//        return true;
//    }
}



