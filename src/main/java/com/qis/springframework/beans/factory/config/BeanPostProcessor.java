package com.qis.springframework.beans.factory.config;

/**
 * @author: qishuo
 * @date: 2022/12/8 19:07
 */
public interface BeanPostProcessor {
    /**
     * 在bean初始化方法之前,执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 在bean对象执行初始化方法之后,执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
