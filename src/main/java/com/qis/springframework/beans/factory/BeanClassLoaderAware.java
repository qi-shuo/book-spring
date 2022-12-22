package com.qis.springframework.beans.factory;

/**
 * 允许 bean 知道 bean class loader的回调；
 * @author: qishuo
 * @date: 2022/12/22 10:52
 */
public interface BeanClassLoaderAware extends Aware {
    /**
     * 将bean classLoader提供给bean的回调
     * @param classLoader
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
