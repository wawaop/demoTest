package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * mybatis-plus的实体
 */
@Component
@TableName("test_table")
public class TableEntity {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String testContext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestContext() {
        return testContext;
    }

    public void setTestContext(String testContext) {
        this.testContext = testContext;
    }
}
