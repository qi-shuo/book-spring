package com.qis.springframework.beans.factory;

/**
 * 实现此接口的 Bean 对象，会在 BeanFactory 设置属性后作出相应的处理，如：执行自定义初始化，或者仅仅检查是否设置了所有强制属性。
 *
 * @author: qishuo
 * @date: 2022/12/21 15:12
 */
public interface InitializingBean {
    /**
     * bean处理了属性填充后调用,bean的初始化方法
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
