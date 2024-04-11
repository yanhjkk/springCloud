//package com.example.ykcloud.config;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.ykcloud.Common.JsonResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.ObjectUtils;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.concurrent.TimeUnit;
//
//
//@WebFilter(urlPatterns = {"/api/*"})  ///api/*
////@WebFilter(urlPatterns = {""})  ///api/*
//public class LoginFilter implements Filter {
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException{
//
//    }
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        response.setHeader("Access-Control-Allow-Origin", "*");  // 解决跨域访问报错
//        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");  //设置过期时间
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With,Content-Type, Accept,token,tel,client_id,identify, uuid, Authorization");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
//        response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0");
//        response.setHeader("Access-Control-Expose-Headers","X-forwared-port, X-forwarded-host");
//        response.setHeader("Vary","Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
//        //需要放行的端口
//        String path = "/notify/notify" +
//                "/notify/walletNotify" +
//                "/notify/selectWallet"+
//                "/api/dl"+
//                "/api/addWinSynthesis" +
//                "/api/addWinDraw"+
//                "/api/addLuckyNum"+
//                "/api/addAuthorityNum"+
//                "/api/addNewUser"+
//                "/api/addLuckyNumPeople"+
//                "/api/addBatchBuyRecord"+
//                "/api/addBatchBlindBox"+
//                "/api/addPurchase"+
//                "/api/addSynthesis"+
//                "/api/upLoadHeader"+
//                "/api/ysAp"+
//                "/api/ysP"+
//                "/api/ztan/art-list"+
//                "/api/ztan/user-hold-list"+
//                "/api/winUserList"+
//                "/api/ztan/trade-list";
//        String token = request.getHeader("token");
//        String tel = request.getHeader("tel");
//        String uri = request.getRequestURI();
//
//        if (path.contains(uri)) {
//            filterChain.doFilter(servletRequest,servletResponse); // 放行请求
//        }else if (ObjectUtils.isEmpty(token) || ObjectUtils.isEmpty(tel)){
//            String s = JSONObject.toJSONString(new JsonResult("100", null, "请先登录"));
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter printWriter = response.getWriter();
//            printWriter.write(s);
//        }else {
//            Long expire = redisTemplate.getExpire(Constants.USER_LOGIN_TOKEN + tel);
//            if (expire <= 0){
//                String s = JSONObject.toJSONString(new JsonResult("300", null, "token过期,请重新登录"));
//                response.setContentType("application/json;charset=utf-8");
//                PrintWriter printWriter = response.getWriter();
//                printWriter.write(s);
//            }else {
//                String oneToken = (String) redisTemplate.opsForValue().get(Constants.USER_LOGIN_TOKEN + tel);
//                if (!token.equals(oneToken)){
//                    String s = JSONObject.toJSONString(new ReturnPojo("200", null, "该账号已在其他设备登录,请重新登录或修改密码"));
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter printWriter = response.getWriter();
//                    printWriter.write(s);
//                }else{
//                    redisTemplate.expire(Constants.USER_LOGIN_TOKEN + tel,3, TimeUnit.DAYS);
//                    filterChain.doFilter(servletRequest,servletResponse);
//                }
//
//            }
//        }
//     }
//    @Override
//    public void destroy() {
//
//    }
//}
