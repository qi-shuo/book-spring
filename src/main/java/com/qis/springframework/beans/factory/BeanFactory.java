package com.qis.springframework.beans.factory;

import com.qis.springframework.beans.BeansException;

/**
 * bean工厂顶级接口
 *
 * @author: qishuo
 * @date: 2022/12/5 18:34
 */
public interface BeanFactory {
    /**
     * 通过name获取bean信息
     *
     * @param beanName bean名称
     * @return bean信息
     * @throws BeansException 获取bean异常
     */
    Object getBean(String beanName) throws BeansException;
}
