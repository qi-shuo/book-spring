package com.qis.springframework.beans.factory;

import com.qis.springframework.beans.BeansException;

/**
 * bean工厂顶级接口
 *
 * @author: qishuo
 * @date: 2022/12/5 18:34
 */
public interface BeanFactory {
    /**
     * 通过name获取bean信息
     *
     * @param beanName bean名称
     * @return bean信息
     * @throws BeansException 获取bean异常
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 返回含有构造函数的bean
     *
     * @param beanName beanName
     * @param args     构造函数入参
     * @return 对应的bean
     * @throws BeansException 抛出异常
     */
    Object getBean(String beanName, Object... args) throws BeansException;

    /**
     * 返回指定泛型的对象
     *
     * @param beanName     要检索的beanName
     * @param requiredType 类型
     * @param <T>          泛型
     * @return 实例化的Bean对象
     * @throws BeansException 不能获取Bean对象,则抛出异常
     */
    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;
}
