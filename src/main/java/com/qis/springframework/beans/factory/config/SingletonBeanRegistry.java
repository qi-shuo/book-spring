package com.qis.springframework.beans.factory.config;

/**
 * 单例bean的注册
 *
 * @author: qishuo
 * @date: 2022/12/5 18:37
 */
public interface SingletonBeanRegistry {

    /**
     * 返回在给定名称下注册的（原始）单例对象。
     *
     * @param beanName 要查找的bean的名称
     * @return 返回注册的单例对象
     */
    Object getSingleton(String beanName);

    /**
     * 注册单例bean
     *
     * @param beanName        bean对象名称
     * @param singletonObject bean对象
     */
    void registerSingleton(String beanName, Object singletonObject);
}

