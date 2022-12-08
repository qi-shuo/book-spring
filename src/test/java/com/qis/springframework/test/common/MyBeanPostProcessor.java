package com.qis.springframework.test.common;

import com.qis.springframework.beans.factory.config.BeanPostProcessor;
import com.qis.springframework.test.bean.UserService;

/**
 * @author: qishuo
 * @date: 2022/12/8 19:48
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if ("userService".equals(beanName)) {
            System.out.println("BeanPostProcessor,初始化之前的后置处理器执行");

        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if ("userService".equals(beanName)) {
            System.out.println("BeanPostProcessor,初始化之后的后置处理器执行");
        }
        return bean;
    }
}
