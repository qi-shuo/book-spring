package com.qis.springframework.test;

import com.qis.springframework.beans.PropertyValue;
import com.qis.springframework.beans.PropertyValues;
import com.qis.springframework.beans.factory.BeanFactory;
import com.qis.springframework.beans.factory.config.BeanDefinition;
import com.qis.springframework.beans.factory.config.BeanReference;
import com.qis.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.qis.springframework.test.bean.UserDao;
import com.qis.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author: qishuo
 * @date: 2022/12/5 19:09
 */
public class ApiTest {
    @Test
    public void testBeanFactory() {
        //创建bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //定义BeanDefinition并且注册
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);
        //第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        System.out.println(userService);
        //第二次获取bean,证明单例
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        System.out.println(userService1);

    }

    /**
     * 测试带有构造函数的bean创建
     */
    @Test
    public void testBeanFactoryArgs() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService", "qis");
        System.out.println(userService);
        userService.queryUserInfo();
    }

    /**
     * 测试bean工厂设置属性
     */
    @Test
    public void testBeanFactoryPropertyValue() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        //设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        //设置userService的beanDefinition
        BeanDefinition userSeriveBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        //注册userService的beanDefinition
        defaultListableBeanFactory.registryBeanDefinition("userService", userSeriveBeanDefinition);

        BeanDefinition userDaoBeanDefinition = new BeanDefinition(UserDao.class);
        defaultListableBeanFactory.registryBeanDefinition("userDao", userDaoBeanDefinition);

        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");

        userService.queryUserInfo();
    }
}
