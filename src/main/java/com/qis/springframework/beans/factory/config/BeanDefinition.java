package com.qis.springframework.beans.factory.config;

/**
 * bean的定义
 *
 * @author: qishuo
 * @date: 2022/12/5 18:30
 */
public class BeanDefinition {
    /**
     * bean的class信息
     */
    private Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
