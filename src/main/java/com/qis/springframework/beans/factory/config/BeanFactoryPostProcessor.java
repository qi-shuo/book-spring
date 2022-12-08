package com.qis.springframework.beans.factory.config;

import com.qis.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * beanFactory的后置处理器
 *
 * @author: qishuo
 * @date: 2022/12/8 18:44
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有BeanDefinition加载完成后,实例化Bean对象之前,调用,提供修改beanDefinition
     *
     * @param beanFactory
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
