package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.BeanFactory;
import com.qis.springframework.beans.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * 抽象bean工厂,继承默认单例bean的注册,实现bean工厂接口
 *
 * @author: qishuo
 * @date: 2022/12/5 18:48
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) throws BeansException {
        Object bean = getSingleton(beanName);
        if (Objects.nonNull(bean)) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    /**
     * 通过beanName获取bean的定义
     *
     * @param beanName bean名称
     * @return bean定义
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 通过beanDefinition,创建bean
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     * @return bean
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}