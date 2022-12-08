package com.qis.springframework.beans.factory;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.qis.springframework.beans.factory.config.BeanDefinition;
import com.qis.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 大多数可列出的 bean 工厂要实现的配置接口。除了ConfigurableBeanFactory之外，它还提供分析和修改 bean 定义以及预实例化单例的工具。
 * org.springframework.beans.factory.BeanFactory的这个子接口并不意味着在正常的应用程序代码中使用：在典型用例中坚持使用org.springframework.beans.factory.BeanFactory或ListableBeanFactory 。
 * 这个接口只是为了允许框架内部即插即用，即使在需要访问 bean 工厂配置方法时也是如此。
 *
 * @author: qishuo
 * @date: 2022/12/8 18:46
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 返回指定 bean 的已注册 BeanDefinition，允许访问其属性值和构造函数参数值（可以在 bean 工厂后处理期间修改）。
     * 返回的 BeanDefinition 对象不应是副本，而应是在工厂中注册的原始定义对象。这意味着如有必要，它应该可以转换为更具体的实现类型。
     * 注意：此方法不考虑祖先工厂。它仅用于访问该工厂的本地 bean 定义。
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 确保所有非 lazy-init 单例都被实例化，同时考虑FactoryBeans 。如果需要，通常在工厂设置结束时调用。
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
