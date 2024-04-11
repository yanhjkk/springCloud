package com.example.ykcloud.accountAop;

import com.alibaba.fastjson.JSON;
import com.example.ykcloud.annotation.AnnotationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class WebLogAspect {


    // @Pointcut(value = "@annotation(com.example.ykcloud.accountAop.TestContents)")
    @Pointcut("execution(public * com.example.ykcloud.controller.*.*(..)))")
    public void controllerWebLog() {

    }

    @After(value = "controllerWebLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("");
        log.info("");
        log.info("《==================================================请求开始==============================================================》");
        log.info("URL :             " + request.getRequestURL().toString());
        log.info("HTTP_METHOD :     " + request.getMethod());
        log.info("IP :              " + request.getRemoteAddr());
        log.info("TEL :             " + request.getHeader("tel"));
        log.info("CLASS_METHOD :    " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("REQUEST :         " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "res" ,pointcut = "controllerWebLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException {
        log.info("RESPONSE :        " + new ObjectMapper().writeValueAsString(res));
        log.info("《===================================================请求结束==============================================================》");

    }
}