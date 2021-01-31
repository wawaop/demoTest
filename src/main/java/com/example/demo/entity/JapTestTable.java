package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


//@Entity
//声明实体类
//@Table(name = "test_table")
public class JapTestTable extends Model<JapTestTable> {
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(nullable = false, unique = true, length = 32)
//    //长度32，唯一索引，nullable表示true可以为空，false不可以
//    //用来声明实体属性的表字段的定义
//    private String userName;
//
//    @TableId(value = "test_id", type = IdType.ASSIGN_ID)
//    private Long testId;
//
//    private String passWord;
//
//    private String email;
//
//    private String nickName;
//
//    private String regTime;
//
//    @Transient
//    //不映射成列的字段
//    private String desc;
//    //省略get和set方法
//
//    public Long getTestId() {
//        return testId;
//    }
//
//    public void setTestId(Long testId) {
//        this.testId = testId;
//    }
////
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassWord() {
//        return passWord;
//    }
//
//    public void setPassWord(String passWord) {
//        this.passWord = passWord;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getNickName() {
//        return nickName;
//    }
//
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
//
//    public String getRegTime() {
//        return regTime;
//    }
//
//    public void setRegTime(String regTime) {
//        this.regTime = regTime;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
}