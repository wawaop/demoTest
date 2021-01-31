package com.example.demo.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SampleAspect {
    /**
     * 切入点：SampleService继承树中所有方法。
     */
    @Pointcut("execution(* com.li.service.impl..*(..))")
    public void methodePointCut(){
 
    }
 
    @Before("methodePointCut()")
    public void monitor(JoinPoint joinPoint) throws Throwable{
        System.out.println(joinPoint.getStaticPart());
    }
 
    @After("methodePointCut()")
    public void monitor2(JoinPoint joinPoint) throws Throwable{
        System.out.println(joinPoint.getStaticPart());
    }
}