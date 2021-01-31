package com.example.demo.controller;

import com.example.demo.dao.TestDao;
import com.example.demo.entity.TableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AOPTestController {

    @Autowired
    private TestDao testDao;

    private String testAOP;

    @Autowired
    TableEntity tableEntity;

    @GetMapping("/aoptest")
    public String test(){
        List<TableEntity> testList = testDao.selectList(null);
        tableEntity.setTestContext("2233");
        return "AOP测试接口";
    }

    public void setTestAOP(){
        this.testAOP = "123";
    }
}
