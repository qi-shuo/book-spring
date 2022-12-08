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
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断beanDeinition是否已经存在
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取bean定义
     *
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 返回注册表中所用bean名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
