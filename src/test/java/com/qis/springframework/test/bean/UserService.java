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
    private String location;

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

    public String queryUserInfo() {
        return userDao.queryUserName(uid);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
