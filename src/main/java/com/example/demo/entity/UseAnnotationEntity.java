package com.example.demo.entity;

import com.example.demo.annotation.AnnotationFieldTest;
import com.example.demo.annotation.AnnotationTest;

@AnnotationTest(title = "测试使用自定义注解实体类", isTtrue = true)
public class UseAnnotationEntity {
    @AnnotationFieldTest(fieldName = "entitiName1", filedVlue = "测试属性1")
    private String entitiName1;

    @AnnotationFieldTest(fieldName = "entitiName2", filedVlue = "测试属性2")
    private String entitiName2;

    private String entitiName3;
}
