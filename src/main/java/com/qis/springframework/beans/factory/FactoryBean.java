package com.qis.springframework.beans.factory;

/**
 * 如果一个 bean 实现了这个接口，它就被用作一个对象的工厂来暴露，而不是直接作为一个将暴露自己的 bean 实例。
 * @author: qishuo
 * @date: 2022/12/22 16:33
 */
public interface FactoryBean<T> {
    /**
     * 获取bean对象
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取bean的class
     * @return
     */
    Class<?> getObjectType();

    /**
     * 判断是否是单例bean
     * @return
     */
    boolean isSingleton();
}
