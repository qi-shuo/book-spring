package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.config.BeanDefinition;

/**
 * 具有抽象自动装配功能的 Bean 工厂
 *
 * @author: qishuo
 * @date: 2022/12/5 19:00
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {
            bean = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //注册单例bean,功能继承DefaultSingletonBeanRegistry
        registerSingleton(beanName, bean);
        return bean;
    }
}
