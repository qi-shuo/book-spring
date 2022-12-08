package com.qis.springframework.beans.factory.config;

import com.qis.springframework.beans.PropertyValues;

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
    /**
     * 属性信息集合
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, new PropertyValues());
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
