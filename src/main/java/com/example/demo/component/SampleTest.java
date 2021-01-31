package com.example.demo.component;

import com.example.demo.service.SampleService;
import com.example.demo.service.SampleServiceImpl;
import org.junit.jupiter.api.Test;

public class SampleTest {
    @Test
    public void testAspect(){
        SampleService sampleService = new SampleServiceImpl();
        System.out.println(sampleService.getName("aaa"));
    }
}