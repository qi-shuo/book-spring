package com.qis.springframework.beans.factory.config;

import com.qis.springframework.beans.PropertyValues;

/**
 * bean的定义
 *
 * @author: qishuo
 * @date: 2022/12/5 18:30
 */
public class BeanDefinition {
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
    /**
     * bean的class信息
     */
    private Class<?> beanClass;
    /**
     * 属性信息集合
     */
    private PropertyValues propertyValues;
    /**
     * bean的初始化方法
     */
    private String initMethodName;
    /**
     * 销毁方法
     */
    private String destroyMethodName;
    /**
     * 作用域,默认单例
     */
    private String scope = SCOPE_SINGLETON;
    /**
     * 单例
     */
    private boolean singleton = true;
    /**
     * 多例
     */
    private boolean prototype = false;


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

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }
}
