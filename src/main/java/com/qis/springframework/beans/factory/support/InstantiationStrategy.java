package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略,用于创建实例,有两种cglib和jdk两种方式创建对象
 * spring源码中默认使用jdk,特殊情况使用cglib(lookMethod)
 * spring创建是new CglibSubclassingInstantiationStrategy(),但是CglibSubclassingInstantiationStrategy继承了SimpleInstantiationStrategy
 *
 * @author: qishuo
 * @date: 2022/12/5 19:26
 */
public interface InstantiationStrategy {
    /**
     * 创建实例
     *
     * @param beanDefinition bean的定义
     * @param beanName       beanName
     * @param ctor           构造函数
     * @param args           构造函数参数
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args);
}
