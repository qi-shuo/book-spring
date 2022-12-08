package com.qis.springframework.beans.factory.config;

import com.qis.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 大多数bean 工厂要实现的配置接口。除了BeanFactory接口中的 bean 工厂客户端方法之外，还提供配置 bean 工厂的工具。
 * 这个 bean 工厂接口并不意味着在正常的应用程序代码中使用：坚持使用BeanFactory或org.springframework.beans.factory.ListableBeanFactory以满足典型需求。
 * 这个扩展接口只是为了允许框架内部的即插即用和对 bean 工厂配置方法的特殊访问。
 *
 * @author: qishuo
 * @date: 2022/12/8 19:04
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROPERTY = "prototype";

    /**
     * 向beanFactory添加BeanPostProcessor
     * 添加一个新的 BeanPostProcessor ，它将应用于此工厂创建的 bean。在出厂配置期间调用。
     * 注意：这里提交的后处理器会按照注册的顺序申请；通过实现org.springframework.core.Ordered接口表达的任何排序语义都将被忽略。
     * 请注意，自动检测的后处理器（例如，作为 ApplicationContext 中的 bean）将始终在以编程方式注册的后处理器之后应用
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
