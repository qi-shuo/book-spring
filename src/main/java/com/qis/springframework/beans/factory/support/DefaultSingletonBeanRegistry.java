package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.DisposableBean;
import com.qis.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
     * 销毁的bean集合
     */
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

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

    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeans.put(beanName, disposableBean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        String[] disposableBeanNames = keySet.toArray(new String[0]);
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
