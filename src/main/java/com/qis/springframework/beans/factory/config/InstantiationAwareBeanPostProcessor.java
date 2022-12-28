package com.qis.springframework.beans.factory.config;

import com.qis.springframework.beans.BeansException;

/**
 * {@link BeanPostProcessor} 的子接口，它添加了一个实例化前回调，以及一个在实例化之后但在显式属性设置或自动装配发生之前的回调
 * Spring中包含其他方法,在实例化之前调用和实例化之后调用和设置属性之前调用
 * @author: qishuo
 * @date: 2022/12/28 14:43
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    /**
     * 在目标 bean 被实例化之前<i>应用这个 BeanPostProcessor<i>。返回的 bean 对象可能是代替目标 bean 使用的代理，有效地抑制了目标 bean 的默认实例化
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
