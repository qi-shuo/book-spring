package com.qis.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.qis.springframework.beans.factory.DisposableBean;
import com.qis.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 因为销毁是容器关闭时钩子方法触发,而且销毁实现有不同形式,比如实现接口DisposableBean或者定义销毁方法,所以DisposableBeanAdapter进行统一适配
 * 初始化方法虽然也有多种,但是是在创建bean时候触发的可以直接调用不同的初始化方法,但是销毁不行,是容器统一调用的
 * <p>
 * Spring中也实现了Runnable接口
 *
 * @author: qishuo
 * @date: 2022/12/21 17:24
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //1. 实现了DisposableBean接口
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        //2. 注解配置destroy-method,判断是为了避免二次执行销毁
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            //执行销毁方法
            destroyMethod.invoke(bean);
        }
    }
}
