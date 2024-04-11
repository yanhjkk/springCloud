package com.example.ykcloud.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationTest {
    String type() default "";
    String name() default "";
}
