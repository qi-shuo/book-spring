package com.qis.springframework.beans.factory.support;

import com.qis.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * cglib创建实例化,spring默认使用SimpleInstantiationStrategy,特殊情况使用CglibSubclassingInstantiationStrategy
 * 比如(lookMethod)
 * spring源码中默认使用jdk,特殊情况使用cglib(lookMethod)
 * spring创建是new CglibSubclassingInstantiationStrategy(),但是CglibSubclassingInstantiationStrategy继承了SimpleInstantiationStrategy
 *
 * @author: qishuo
 * @date: 2022/12/7 19:29
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == ctor) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
