package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.config.BeanDefinition;
import com.qis.springframework.beans.factory.config.BeanPostProcessor;
import com.qis.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.qis.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 抽象bean工厂,继承默认单例bean的注册,实现bean工厂接口
 *
 * @author: qishuo
 * @date: 2022/12/5 18:48
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * ClassLoader to resolve bean class names with, if necessary
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return (T) getBean(beanName);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object bean = getSingleton(beanName);
        if (Objects.nonNull(bean)) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }

    /**
     * 通过beanName获取bean的定义
     *
     * @param beanName bean名称
     * @return bean定义
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 通过beanDefinition,创建bean
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     * @param args           构造函数入参
     * @return bean
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        //Spring源码也是先移除,后添加,也就是如果同一个beanPostProcessor添加两次,那他执行的优先级是以后添加的位置为准的
        //从旧位置移除，如果有的话
        this.beanPostProcessors.remove(beanPostProcessor);
        //添加到list末尾
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
