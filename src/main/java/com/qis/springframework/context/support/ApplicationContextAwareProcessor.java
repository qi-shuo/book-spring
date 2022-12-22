package com.qis.springframework.context.support;

import com.qis.springframework.beans.factory.config.BeanPostProcessor;
import com.qis.springframework.context.ApplicationContext;
import com.qis.springframework.context.ApplicationContextAware;

/**
 * 通过BeanPostProcessor实现感知应用上下文对象
 * ApplicationContextAware 实现借助Bean的后置处理器实现的,个人理解其他xxxAware接口是在beanFactory下面,ApplicationContextAware属于context包
 *
 * @author: qishuo
 * @date: 2022/12/22 11:23
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
