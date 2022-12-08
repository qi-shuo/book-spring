package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.core.io.Resource;
import com.qis.springframework.core.io.ResourceLoader;

/**
 * Bean 定义加载器
 *
 * @author: qishuo
 * @date: 2022/12/8 15:50
 */
public interface BeanDefinitionReader {
    /**
     * 获取BeanDefinition的注册器
     *
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     *
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 通过Resource加载bean 定义
     *
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 通过多个resource加载bean 定义
     *
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 通过location加载bean 定义
     *
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;
}
