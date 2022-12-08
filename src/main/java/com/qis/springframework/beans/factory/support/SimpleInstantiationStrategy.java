package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 使用JDK创建实例,spring默认也是这种方式
 * spring源码中默认使用jdk,特殊情况使用cglib(lookMethod)
 * spring创建是new CglibSubclassingInstantiationStrategy(),但是CglibSubclassingInstantiationStrategy继承了SimpleInstantiationStrategy
 * 只有特殊情况才会使用cglib的方式
 *
 * @author: qishuo
 * @date: 2022/12/7 19:23
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) {
        Class<?> clazz = beanDefinition.getBeanClass();
        try {
            if (ctor != null) {
                clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]");
        }

        return null;
    }
}
