package com.example.ykcloud.config;



import com.example.ykcloud.Service.UserService;
import com.example.ykcloud.annotation.PassToken;
import com.example.ykcloud.annotation.UserLoginToken;
import com.example.ykcloud.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Controller
public class AuthenticationInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("=========================启动token拦截器======================");
        String token = TokenUtils.getToken();
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                //校验token有效性
                Boolean verify = false;
                try {
                    verify = TokenUtils.verifyToken(token);
                } catch (Exception e) {
                    verify = false;
                    throw new RuntimeException("无效token");
                }
                if(!verify){
                    throw new RuntimeException("token校验失败");
                }
//                // 获取 token 中的 userid
//                if (verify) {
//                    String userId;
//                    try {
//                        userId = TokenUtils.getUserIdByToken(token);
//                    } catch (JWTDecodeException j) {
//                        throw new RuntimeException("401");
//                    }
//                    User user = userService.getUserByUserId(Integer.valueOf(userId));
//                    if (user == null) {
//                        throw new RuntimeException("用户不存在，请重新登录");
//                    }
//                } else {
//                    throw new RuntimeException("token校验失败");
//                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}


