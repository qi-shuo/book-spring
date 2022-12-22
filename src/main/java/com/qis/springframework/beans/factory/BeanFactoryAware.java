package com.qis.springframework.beans.factory;

import com.qis.springframework.beans.BeansException;

/**
 * 实现此接口,感知所属的BeanFactory
 *
 * @author: qishuo
 * @date: 2022/12/22 10:54
 */
public interface BeanFactoryAware extends Aware {

    /**
     * 实现此接口,感知所属的BeanFactory
     *
     * @param beanFactory
     * @throws BeansException
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
