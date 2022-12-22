package com.qis.springframework.beans.factory;

/**
 * 可以获取beanName
 *
 * @author: qishuo
 * @date: 2022/12/22 11:03
 */
public interface BeanNameAware extends Aware {
    /**
     * 感知beanName
     * @param name
     */
    void setBeanName(String name);
}
