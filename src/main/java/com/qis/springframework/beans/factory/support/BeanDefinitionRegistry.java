package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.factory.config.BeanDefinition;

/**
 * bean定义的住蹙
 *
 * @author: qishuo
 * @date: 2022/12/5 18:42
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册BeanDefinition
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
