package com.qis.springframework.test.bean;

import lombok.ToString;

/**
 * @author: qishuo
 * @date: 2022/12/5 19:10
 */
@ToString
public class UserService {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {

    }


    public void queryUserInfo() {
        System.out.println("查询用户信息");
    }

}
