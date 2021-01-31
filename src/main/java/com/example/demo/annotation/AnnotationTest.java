package com.example.demo.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
    String title() default "test";
    boolean isTtrue();

}
