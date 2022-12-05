package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认单例bean的注册
 *
 * @author: qishuo
 * @date: 2022/12/5 18:44
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * 单例bean存储map
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 获取单例bean
     *
     * @param beanName 要查找的bean的名称
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 注册单例bean
     *
     * @param beanName        bean对象名称
     * @param singletonObject bean对象
     */
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
