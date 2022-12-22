package com.qis.springframework.test.bean;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.*;
import com.qis.springframework.context.ApplicationContext;
import com.qis.springframework.context.ApplicationContextAware;
import lombok.ToString;

/**
 * @author: qishuo
 * @date: 2022/12/5 19:10
 */
@ToString
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private String name;
    private String uid;
    private UserDao userDao;
    private String location;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

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

    @Override
    public void destroy() throws Exception {
        System.out.println("执行了销毁方法destroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("执行了初始化方法afterPropertiesSet");
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader：" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory 执行" );
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("setApplicationContext 执行" );
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
