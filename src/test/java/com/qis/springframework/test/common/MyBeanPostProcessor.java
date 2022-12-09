package com.qis.springframework.test.common;

import com.qis.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author: qishuo
 * @date: 2022/12/8 19:48
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        System.out.println(beanName + " :BeanPostProcessor,初始化之前的后置处理器执行");


        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        System.out.println("BeanPostProcessor,初始化之后的后置处理器执行");

        return bean;
    }
}
