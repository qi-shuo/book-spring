package com.qis.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.PropertyValue;
import com.qis.springframework.beans.PropertyValues;
import com.qis.springframework.beans.factory.config.BeanDefinition;
import com.qis.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 具有抽象自动装配功能的 Bean 工厂
 *
 * @author: qishuo
 * @date: 2022/12/5 19:00
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * Spring中虽然创建使用CglibSubclassingInstantiationStrategy,
     * 但是CglibSubclassingInstantiationStrategy继承SimpleInstantiationStrategy,所以默认使用jdk
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean;
        try {
            //创建Bean
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 设置属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //注册单例bean,功能继承DefaultSingletonBeanRegistry
        registerSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            //判断使用构造函数比较简单,实际肯定复杂
            if (null != args && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }

        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 为bean设置属性
     *
     * @param beanName       bean名称
     * @param bean           bean实例
     * @param beanDefinition bean定义
     */
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    //A依赖B,获取B的实例化
                    value = getBean(((BeanReference) value).getBeanName());
                }
                //属性填充(借助第三方工具包)
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values: " + beanName);
        }

    }
}
