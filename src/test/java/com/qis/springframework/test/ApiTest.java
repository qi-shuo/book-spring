package com.qis.springframework.test;

import com.qis.springframework.beans.factory.BeanFactory;
import com.qis.springframework.beans.factory.config.BeanDefinition;
import com.qis.springframework.beans.factory.support.DefaultListableBeanFactory;
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
}
