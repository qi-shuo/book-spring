package com.qis.springframework.context.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.qis.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 抽象基类刷新应用上下文
 *
 * @author: qishuo
 * @date: 2022/12/9 16:38
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    /**
     * 加载beanDefinition
     *
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
}
