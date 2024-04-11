package com.example.ykcloud.annotation.aop;

import com.alibaba.fastjson.JSON;
import com.example.ykcloud.annotation.AnnotationTest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AopTest {
    @Pointcut(value = "@annotation(com.example.ykcloud.annotation.AnnotationTest)")
    public void aopPointCut() {
        log.info("&&&&&&&&&&");
    }

    @After(value = "aopPointCut()")
    public void aopTest(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AnnotationTest annotation = method.getAnnotation(AnnotationTest.class);
        Object[] args = joinPoint.getArgs();
        String s = JSON.toJSONString(args);
        String name = annotation.name();
        String type = annotation.type();

        log.info("注解测试{}", type);
        log.info("参数打印{}", s);
    }

    @Before(value = "aopPointCut()")
    public void before() {
        System.out.println("后置通知");
    }


//@Around("aopPointCut()")
//public Object around(ProceedingJoinPoint joinPoint){
///**
// * 1、获取参数进行加密
// */
//    Object[] encrypt = encrypt(joinPoint);
//    /**
//     * 2、修改请求参数,执行目标方法,业务操作加密数据
//     */
//    Object result = joinPoint.proceed(encrypt); // 目标方法返回值
//    /**
//     * 3、修改返回值,返回前端为解密数据
//     */
//    //Object decrypt = decrypt(result);
//   // return decrypt;
//}
//    public Object[] encrypt(ProceedingJoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//
//        if (args.length != 0) {
//            for (int i = 0; i < args.length; i++) {
//                Object o = args[i];
//                if (o instanceof String) {
//                  //  args[i] = stringEncryptor.encrypt(String.valueOf(o));
//                }
//            }
//        }
//
//        return args;
//    }

}
