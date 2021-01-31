package com.example.demo.service;

import com.example.demo.annotation.AnnotationFieldTest;
import com.example.demo.annotation.AnnotationTest;
import com.example.demo.entity.UseAnnotationEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * 测试自定义属性的获取
 */
public class AnnotationJunitTest {
    @Test
    public void getUserAnnotationField(){
        try{
            // 获取使用了该类对象
            Class entityClass = Class.forName("com.example.demo.entity.UseAnnotationEntity");
            if (entityClass.isAnnotationPresent(AnnotationTest.class)) {
                // 如果使用了类注解

                // 获取类注解上的内容
                AnnotationTest annotationTest = (AnnotationTest) entityClass.getAnnotation(AnnotationTest.class);
                String title = annotationTest.title();
                boolean isTrue = annotationTest.isTtrue();
                System.out.println("类注解上的内容为：titile:" + title + "   isTrue:" + isTrue);

                // 获取实体里面的属性注解
                Field[] entityField = entityClass.getDeclaredFields();
                for(Field field : entityField){
                    if(field.isAnnotationPresent(AnnotationFieldTest.class)){
                        // 如果被AnnotationFieldTest注解标注了的话，获取注解的内容
                        AnnotationFieldTest annotationFieldTest = field.getAnnotation(AnnotationFieldTest.class);
                        String fieldName = annotationFieldTest.fieldName();
                        String fieldValue = annotationFieldTest.filedVlue();
                        System.out.println("属性" + field.getName() + "的注解内容：" + "fieldName:" + fieldName + "   fielddValue:" + fieldValue);
                    }
                }
                System.out.println();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
