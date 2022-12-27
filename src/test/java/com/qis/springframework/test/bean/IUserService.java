package com.qis.springframework.test.bean;

/**
 * @author: qishuo
 * @date: 2022/12/27 15:30
 */
public interface IUserService {
    String queryUserInfo();

    String register(String userName);
}
