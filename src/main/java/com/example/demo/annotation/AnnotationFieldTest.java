package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * 属性注解测试
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationFieldTest {
    String fieldName();

    String filedVlue();
}
