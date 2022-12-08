package com.qis.springframework.beans.factory.config;

/**
 * 表示对类型的 bean 引用,设置属性使用
 *
 * @author: qishuo
 * @date: 2022/12/8 14:30
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
