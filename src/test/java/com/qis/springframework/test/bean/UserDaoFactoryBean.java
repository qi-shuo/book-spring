package com.qis.springframework.test.bean;

import com.qis.springframework.beans.factory.FactoryBean;

/**
 * @author: qishuo
 * @date: 2022/12/22 17:27
 */
public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    @Override
    public UserDao getObject() throws Exception {
        return new UserDao();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
