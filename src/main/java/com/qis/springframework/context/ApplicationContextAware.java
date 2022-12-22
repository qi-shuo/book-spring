package com.qis.springframework.context;

import com.qis.springframework.beans.factory.Aware;

/**
 * 实现此接口，既能感知到所属的 ApplicationContext
 * ApplicationContextAware 实现借助Bean的后置处理器实现的,个人理解其他xxxAware接口是在beanFactory下面,ApplicationContextAware属于context包
 * @author: qishuo
 * @date: 2022/12/22 11:07
 */
public interface ApplicationContextAware extends Aware {
    /**
     * 既能感知到所属的 ApplicationContext
     *
     * @param applicationContext
     */
    void setApplicationContext(ApplicationContext applicationContext);
}
