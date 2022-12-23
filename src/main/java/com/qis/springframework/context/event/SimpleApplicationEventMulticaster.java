package com.qis.springframework.context.event;

import com.qis.springframework.beans.factory.BeanFactory;
import com.qis.springframework.context.ApplicationEvent;
import com.qis.springframework.context.ApplicationListener;

/**
 * 简单的实现事件广播器
 * @author: qishuo
 * @date: 2022/12/23 15:51
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(ApplicationEvent applicationEvent) {
        for (ApplicationListener applicationListener : getApplicationListeners(applicationEvent)) {
            applicationListener.onApplicationEvent(applicationEvent);
        }
    }
}
