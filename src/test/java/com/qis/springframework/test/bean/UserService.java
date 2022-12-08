package com.qis.springframework.test.bean;

import lombok.ToString;

/**
 * @author: qishuo
 * @date: 2022/12/5 19:10
 */
@ToString
public class UserService {
    private String name;
    private String uid;
    private UserDao userDao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {

    }

    public void queryUserInfo() {
        System.out.println(userDao.queryUserName(uid));
    }
}
